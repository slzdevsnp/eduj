package org.slzdevsnp.concurrency.thwaking;

public class MasterSlaveAwakePlay  {

    public static void main(String[] args) {
        Thread slave = new Thread(new SlaveThread());
        Thread master = new Thread(new MasterThread(slave));
        slave.start();
        master.start();
        try {
            slave.join();
            master.join();
        }
        catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("done.");
    }
}
