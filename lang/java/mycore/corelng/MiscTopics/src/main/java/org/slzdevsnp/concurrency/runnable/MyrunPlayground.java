package org.slzdevsnp.concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MyrunPlayground {

    public static void main(String[] args) throws InterruptedException {

        /*System.out.println("#### running in same thread");
        runInTheSameThread();
        Thread.sleep(3000L);*/

        /*System.out.println("#### running in separate thread");
        runInSeparateThread();
        Thread.sleep(2000L);
        */


        System.out.println("### run a loop of MyOtherRunnable");
        runFewIterations();
    }

    private static void runInTheSameThread() {
        MyRunnable myR = new MyRunnable(8);
        log.info("thread: {} start MyRunnable run", Thread.currentThread().getId());
        myR.run();
        log.info("thread: {} end MyRunnable run", Thread.currentThread().getId());
    }

    private static void runInSeparateThread() {
        MyRunnable myR = new MyRunnable(8);

        log.info("thread: {} firing MyRunnable run", Thread.currentThread().getId());
        Thread thread = new Thread(myR);
        thread.start();
        log.info("thread: {} fired MyRunnable run", Thread.currentThread().getId());
    }

    private static void runFewIterations() throws InterruptedException {
        long checkSleepInteval = 300L;
        int  decayInSeconds = 5;
        List<Integer> ids = Arrays.asList(101, 102, 103);

        logIteration(0);
        MyOtherRunnable myoR = new MyOtherRunnable(ids, decayInSeconds, checkSleepInteval);
        myoR.start();

        Thread.sleep((decayInSeconds +2) * 1000L);

        logIteration(1);
        myoR = new MyOtherRunnable(ids, decayInSeconds, checkSleepInteval);
        myoR.start();
        Thread.sleep(2000L);
        ids = Arrays.asList(101, 102, 103, 104);
        myoR.setIds(ids);


    }

    private static void logIteration(int i){
        log.info("thread: {} grand iteration: {}, firing MyOtherRunnable run",
                Thread.currentThread().getId(), i);
    }
}
