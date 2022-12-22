package org.paumard.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcucerConsumerWithLocks {

	public static void main(String[] args) throws InterruptedException {

		final int BUFFER_SIZE=10;
		List<Integer> buffer = new ArrayList<>();

		Lock lock = new ReentrantLock(); //standard implementation
		Condition isEmptyCond = lock.newCondition();
		Condition isFullCond = lock.newCondition();


		class Consumer implements Callable<String> {

			public String call() throws InterruptedException, TimeoutException {
				int count = 0;
				while (count++ < BUFFER_SIZE) {
					try {
						lock.lock();
						while (isEmpty(buffer)) {
							// wait
							if (!isEmptyCond.await(10, TimeUnit.MILLISECONDS)) {
								throw new TimeoutException("Consumer time out");
							}
						}
						buffer.remove(buffer.size() - 1);
						printStatus("consumer",buffer);
						// signal at this stage buffer is not full, so signal condition
						isFullCond.signalAll();
					} finally {
						lock.unlock();
					}
				}
				return "### Consumed " + (count - 1);
			}
		}

		class Producer implements Callable<String> {

			public String call() throws InterruptedException, TimeoutException {
				int count = 0;
				while (count++ < BUFFER_SIZE) {
					try {
						lock.lock();

						//int i = 10/0;  //this will raise an exeption and no data will be produced

						//Thread.sleep(20l); //simulate abnormally long execution and triggering exceptions

						while (isFull(buffer)) {
							// indefinite waiting
							isFullCond.await();
						}
						buffer.add(1);
						printStatus("producer",buffer);
						// signal at this stage buffer is not empty so signalling it
						isEmptyCond.signalAll();
					} finally {
						lock.unlock();
					}
				}
				return "Produced " + (count - 1);
			}
		}

		List<Producer> producers = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			producers.add(new Producer());
		}

		List<Consumer> consumers = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			consumers.add(new Consumer());
		}
		
		System.out.println("Producers and Consumers launched");
		
		List<Callable<String>> producersAndConsumers = new ArrayList<>();
		producersAndConsumers.addAll(producers);
		producersAndConsumers.addAll(consumers);

		ExecutorService executorService = Executors.newFixedThreadPool(8); // 8 = 4 producers and 4 consumers
		try {
			List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

			futures.forEach(
					future -> {
						try {
							System.out.println(future.get()); // a call to each callable
						} catch (InterruptedException | ExecutionException e) {
							System.out.println("Exception: " + e.getMessage());
						} catch (Throwable t){
							System.out.println("cauth thrown exeption " + t.getMessage());
						}
					});
		} finally {
			executorService.shutdown();
			System.out.println("Executor service shut down");
		}

	}

	public static void printStatus(String msg, List<Integer> b){
		System.out.println( "status: " + msg  + " buffer size: " +  b.size()  + " in thread: "  +    Thread.currentThread().getId());
	}

	public static boolean isEmpty(List<Integer> buffer) {
		return buffer.size() == 0;
	}

	public static boolean isFull(List<Integer> buffer) {
		return buffer.size() == 10;
	}
}
