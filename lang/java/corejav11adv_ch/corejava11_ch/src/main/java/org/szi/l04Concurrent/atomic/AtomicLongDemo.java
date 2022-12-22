package org.szi.l04Concurrent.atomic;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class AtomicLongDemo {
    public static AtomicLong count = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        log.info("test1: ");
        test1();
        count.set(0);
        log.info("test2: ");
        test2();
    }

    static Runnable computeTask = () -> {
        for (int k = 1; k <= 100000; k++)  // each task increments count 100 K times
            count.incrementAndGet();
    };
    private static void test1() throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000; i++) { //1000 threads

            executor.execute(computeTask);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final value (expect 100E6:) " + count);
        long end = System.nanoTime();
        System.out.println("took "+ (end - start) * 1E-9 + " seconds");

    }

    private static void test2() throws InterruptedException {
        time( () -> {
            ExecutorService executor = Executors.newCachedThreadPool();
            for (int i = 1; i <= 1000; i++) { //1000 threads
                executor.execute(computeTask);
            }
            executor.shutdown();
            try {
                executor.awaitTermination(15, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Final value (expect 100E6:) " + count);
    }

    static void time(Runnable task){
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.println("task took " + (end-start) * 1E-9 + " seconds..");
    }

}
