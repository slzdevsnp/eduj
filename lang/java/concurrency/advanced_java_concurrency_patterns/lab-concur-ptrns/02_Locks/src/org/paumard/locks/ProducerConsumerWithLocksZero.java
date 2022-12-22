package org.paumard.locks;



import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocksZero {

    private static int[] buffer;
    private static int count;

    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    static class Producer {

        public void produce() {
            try {
                lock.lock();
                while (isFull(buffer)) {
                    notFull.await();
                }
                buffer[count++] = 1;
                printThreadInfoMessage("produced with count: " + count);
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }

    static class Consumer {
        public void consume(){
            try{
                lock.lock();
                while(isEmpty(buffer)) {
                    notEmpty.await();
                }
                buffer[--count] = 0;
                printThreadInfoMessage("consumed with count: " + count);
                notFull.signal();
            } catch(InterruptedException e){
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }


    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    static void printThreadInfoMessage(String msg){
        System.out.println("Thread: " +  Thread.currentThread().getId() + " " + msg);
    }


    public static void main(String... strings) throws InterruptedException {

        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Runnable produceTask = () -> {
            for (int i = 0 ; i < 50 ; i++) {
                producer.produce();
            }
            System.out.println("Done producing");
        };
        Runnable consumeTask = () -> {
            for (int i = 0 ; i < 49 ; i++) {
                consumer.consume();
            }
            System.out.println("Done consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the buffer: " + count);
    }

}
