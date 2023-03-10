package concurrency.latch;

import java.util.concurrent.CountDownLatch;

public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            //starting all 4 workers with the same startSignal, endSignal
            new Thread(new Worker(i, startSignal, endSignal)).start();
        }

        System.out.println("Work done before starting workers...");
        startSignal.countDown(); // Go! on all 5 threqds
        System.out.println("Doing work while workers are running");
        endSignal.await();  //waites untill all threads finished
        System.out.println("All workers finished");
    }
}
