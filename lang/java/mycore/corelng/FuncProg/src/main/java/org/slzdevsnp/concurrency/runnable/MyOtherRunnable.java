  package org.slzdevsnp.concurrency.runnable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class MyOtherRunnable implements Runnable {


    private Thread worker;
    private final AtomicBoolean shutdown = new AtomicBoolean(false);

    private List<Integer> initialIds;
    private List<Integer> ids;
    private long checkInterval; // e.g. 300 ms
    private LocalDateTime startDate;
    private int decaySeconds;

    public MyOtherRunnable(List<Integer> initialIds, int decaySeconds, long sleepInterval) {
        this.initialIds = new ArrayList<>(initialIds); //copy
        this.ids = new ArrayList<>(initialIds);

        this.decaySeconds = decaySeconds;
        this.checkInterval = sleepInterval;
        this.startDate = LocalDateTime.now();
        this.shutdown.set(false);
    }

    public void setIds(List<Integer> ids){
        this.ids = ids;
    }


    public void start() {
        worker = new Thread(this);
        worker.start();
    }


    @SneakyThrows
    @Override
    public void run() {

        log.debug("thread:{}  fired run()", Thread.currentThread().getId());

        // this runnable workload
        log.info("thread: {} fired a payload",
                Thread.currentThread().getId());
        //do check if we need to exit a run
        while (!shutdown.get()) {
            try {
                Thread.sleep(checkInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
            if (Duration.between(startDate, LocalDateTime.now()).toSeconds() > decaySeconds ) {
                log.debug("thread:{} shutdown over decaySeconds at {} s",
                        Thread.currentThread().getId(), decaySeconds);
                shutdown.set(true);
            }
            if ( !this.ids.equals(this.initialIds) ){
                log.debug("thread:{} shutdown over chaged ids: {} ",
                        Thread.currentThread().getId(), this.ids);
                shutdown.set(true);
            }
        }
    }



}
