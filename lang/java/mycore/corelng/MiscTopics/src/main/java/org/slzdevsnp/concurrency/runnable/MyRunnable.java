package org.slzdevsnp.concurrency.runnable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MyRunnable implements Runnable{
    private final int MAX_ITER_DEFAULT = 6;
    private int maxIterations;

    public MyRunnable(int maxIterations){
        this.maxIterations = maxIterations;
    }
    public MyRunnable(){
        this.maxIterations = MAX_ITER_DEFAULT;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info(" thread: {} run() called", Thread.currentThread().getId());

        for(int i = 0; i < maxIterations; i++){
            log.info("thread: {} iter in a loop, counter {}",
                    Thread.currentThread().getId(),  i);
            i++;
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
