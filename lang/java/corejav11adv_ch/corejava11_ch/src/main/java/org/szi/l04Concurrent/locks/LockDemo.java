package org.szi.l04Concurrent.locks;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class LockDemo {
    public static int count;
    public static Lock countLock = new ReentrantLock();



    public static void main(String[] args) throws InterruptedException {
        log.info("with ReentrantLock");
        testWithReentrantLock();
        log.info("with Intrinsic Lock");
        Counter count1 = new Counter();
        testWithIntrinsicLock(count1);  //throws RejectedExecutionException
        log.info("with mono thread. NB! observe that reentrant, intrinsic locking takes time..");
        Counter count2 = new Counter();
        testMonoThread(count2);
    }

    private static void testWithReentrantLock() throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) { //100 threads
            Runnable task = () -> {
                for (int k = 1; k <= 1_000_000; k++) {  // 1K updates per task in trhead
                    countLock.lock();  //first lock
                    try {
                        count++; // Critical section
                    } finally {
                        countLock.unlock(); // Make sure the lock is unlocked
                    }
                }
            };
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        long end = System.nanoTime();
        System.out.println("Final value (exp: 100M):  " + count);
        System.out.println("took " +(end-start)*1E-9 + " seconds");
    }
    private static void testWithIntrinsicLock(Counter cnt) throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) { //100 threads
            Runnable task = () -> {
                for (int k = 1; k <= 1_000_000; k++) {  // 1K updates per task in trhead
                    long x = cnt.increment();
                }
            };
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        long end = System.nanoTime();
        System.out.println("Final value (exp: 100M):  " + cnt.getValue());
        System.out.println("took " +(end-start)*1E-9 + " seconds");
    }

    private static void testMonoThread(Counter cnt){
        long start = System.nanoTime();
        for (int i=1; i<=100; i++){
            for (int k = 1; k<=1_000_000; k++){
                long x = cnt.increment();
            }
        }
        long end = System.nanoTime();
        System.out.println("Final value (exp: 100M):  " + cnt.getValue());
        System.out.println("took " +(end-start)*1E-9 + " seconds");
    }


}