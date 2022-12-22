package org.paumard.atomiccounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterInitial {
    /**
     * this example shows a race condition on a shared variable
     */
    //shared varialbe
    //private static int counter = 0; //unsafe version
    private static AtomicInteger counter = new AtomicInteger(0); //safe version

    public static void main(String[] args) {

        class Incrementer implements  Runnable{

            int maxIter = 1_000_000;

            public void run(){
                for (int i =0 ; i < maxIter ; i++){
                    //counter++;  //unsafe version
                    counter.incrementAndGet(); //safe version
                }
            }
        }

        class Decrementer implements  Runnable{
            int maxIter = 1_000_000;

            public void run(){
                for (int i =0 ; i < maxIter ; i++){
                    //counter--;   //unsafe version
                    counter.decrementAndGet();  //safe version
                }
            }
        }


        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Future<?>> futures = new ArrayList<>();

        try {

            for (int i = 0; i < 4; i++) {
                futures.add(executorService.submit(new Incrementer()));
            }
            for (int i = 0; i < 4; i++) {
                futures.add(executorService.submit(new Decrementer()));
            }

            futures.forEach(
                    future -> {
                        try {
                            future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println(e.getMessage());
                        }
                    }
            );

            System.out.println("counter = " + counter);

        }finally{
            executorService.shutdown();
        }
    }


}
