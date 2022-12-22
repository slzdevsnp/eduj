package org.slzdevsnp.concurrency.thwaking;

public class SlaveThread implements Runnable{
    private final static int SLEEP_TIME = 2000; //ms

    public void run() {
        System.out.println("SlaveThread: run.");
        while (true) {
            System.out.println("SlaveThread: next iteration.");
            try {
                Thread.interrupted(); //clear the interrupted status
                Thread.sleep(SLEEP_TIME);
            }
            catch (InterruptedException e) {
                System.out.println("SlaveThread: wake up.");
            }
        }
    }
}
