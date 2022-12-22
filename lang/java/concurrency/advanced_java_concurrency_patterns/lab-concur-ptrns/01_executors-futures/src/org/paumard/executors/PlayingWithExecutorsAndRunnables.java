package org.paumard.executors;

import java.util.concurrent.*;

public class PlayingWithExecutorsAndRunnables {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //exampleRunnable();
        exampleCallableOne();
    }

    static void exampleCallableOne() throws ExecutionException, InterruptedException, TimeoutException {
        Callable<String> task = () -> {
            Thread.sleep(300L);
            return "I am in thread " + Thread.currentThread().getName();
        };
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
			for (int i = 0; i < 10; i++) {
				Future<String> future = executor.submit(task);
				//System.out.println("I get: " + future.get());
				System.out.println("I get: " + future.get(100, TimeUnit.MILLISECONDS));
			}
		} finally {
			executor.shutdown();
		}
    }

    static void exampleRunnable() {
        Runnable task = () -> System.out.println("I am in thread " + Thread.currentThread().getName());
        // ExecutorService service = Executors.newSingleThreadExecutor(); //all tasks exec in a same thread sequentially
        ExecutorService service = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            // new Thread(task).start();
            service.execute(task);
        }
        service.shutdown();
    }
}
