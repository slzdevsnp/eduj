package org.slzdevsnp.bl_waitandnotify;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Receiver implements Runnable {
    private Data dataInbound;

    public Receiver(Data dataInbound) {
        this.dataInbound = dataInbound;
    }

    public void run() {

        for(String receivedMessage = dataInbound.receive();
            !"end".equalsIgnoreCase(receivedMessage);
            receivedMessage = dataInbound.receive()
        ) {

            log.debug("thread:{}  at {} a receiver rcvd packet: {}",
                    Thread.currentThread().getId(), LocalDateTime.now(), receivedMessage);

            try {
                //Thread.sleep() to mimic heavy server-side processing
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,
                        5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
    }
}