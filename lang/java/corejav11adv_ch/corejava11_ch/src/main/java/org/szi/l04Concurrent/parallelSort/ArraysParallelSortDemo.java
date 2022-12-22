package org.szi.l04Concurrent.parallelSort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ArraysParallelSortDemo {

static void time(Runnable task){
    long start = System.nanoTime();
    task.run();
    long end = System.nanoTime();
    System.out.println("task took " + (end-start) * 1E-9 + " seconds..");
}

    public static void main(String[] args) {
        int SIZE = 100_000_000;
        long[] values = new long[SIZE];

        log.info("Arrays.parallelSort()");
        Arrays.parallelSetAll(values,i ->(int) (SIZE*Math.sin(i))) ;
        time(() -> Arrays.parallelSort(values));

        log.info("Arrays.sort()");
        Arrays.parallelSetAll(values,i ->(int) (SIZE*Math.sin(i))) ;
        time( () -> Arrays.sort(values));
    }

}
