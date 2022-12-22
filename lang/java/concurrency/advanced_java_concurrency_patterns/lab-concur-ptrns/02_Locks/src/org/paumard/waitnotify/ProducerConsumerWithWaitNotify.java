package org.paumard.waitnotify;

public class ProducerConsumerWithWaitNotify {

    private static Object lock = new Object();

    private static int[] buffer;
    private static int count;

    static class Producer {

        void produce() {
            synchronized (lock) {
                if (isFull(buffer)) {
                    try {
                        printThreadInfoMessage("producer will wait now..");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                printThreadInfoMessage("produced with count: " + count);
                lock.notify();
                //lock.notifyAll();
            }
        }
    }


    static class Consumer {

        void consume() {
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    try {
                        printThreadInfoMessage(" consumer will wait now..");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--count] = 0;
                printThreadInfoMessage("consumed with count: " + count);
                lock.notify();
                //lock.notifyAll();
            }
        }
    }
    static void printThreadInfoMessage(String msg){
        System.out.println("Thread: " +  Thread.currentThread().getId() + " " + msg);
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
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
