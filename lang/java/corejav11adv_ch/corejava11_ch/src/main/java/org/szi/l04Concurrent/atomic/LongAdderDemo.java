package org.szi.l04Concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000; i++) { //1000 threads
            Runnable task = () -> {
                for (int k = 1; k <= 100000; k++)  // 100K increments per thread
                    count.increment();
            };
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final value: " + count);
        long end = System.nanoTime();
        System.out.println((end - start) * 1E-9 + " seconds");
    }
}
