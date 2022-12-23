package org.slzdevsnp.bl_waitandnotify;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Sender implements Runnable {
    private Data dataOutbound;

    public Sender(Data dataOutbound) {
        this.dataOutbound = dataOutbound;
    }

    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        for (String packet : packets) {
            dataOutbound.send(packet);
            log.debug("thread:{}  at {} sender sent a packet: {}",
                    Thread.currentThread().getId(), LocalDateTime.now(), packet);

            //Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
    }
}