package org.slzdevsnp.concurrency.thwaking;

public class MasterThread implements Runnable{
    private final static int SLEEP_TIME = 5000; //ms
    private final Thread SLAVE;

    public MasterThread(Thread aSlave) {
        SLAVE = aSlave;
    }

    public void run() {
        System.out.println("MasterThread: run.");
        while (true) {
            System.out.println("MasterThread: call to slave.");
            SLAVE.interrupt(); //wake up
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.err.println("MasterThread: abnormal wake up.");
                return;
            }
        }
    }
}