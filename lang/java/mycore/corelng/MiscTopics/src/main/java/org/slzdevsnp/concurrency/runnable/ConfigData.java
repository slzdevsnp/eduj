package org.slzdevsnp.concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class ConfigData {
    private List<Integer> ids;
    private LocalDateTime startDatetime;
    private int decaySeconds;
    private boolean shouldUpdate = false;
    private long interval  = 100L;

    public ConfigData(List<Integer> ids, int decaySeconds){
        this.ids = ids;
        this.decaySeconds = decaySeconds;
        startDatetime = LocalDateTime.now();
        shouldUpdate = false;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public synchronized  void setIds(List<Integer> ids) {
        if (!ids.equals(this.ids)){
            shouldUpdate = true;
        }
        this.ids = ids;
        notifyAll();
        shouldUpdate = false;
    }

    synchronized  void monitorDecay(){
        while (!shouldUpdate) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
            //check
            if (Duration.between(startDatetime,LocalDateTime.now()).toSeconds() > decaySeconds){
                shouldUpdate = true;
            }
        }
        notifyAll();
        shouldUpdate = false;
    }
}
