package org.szi.l04Concurrent.runnable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@Slf4j
public class RunnableDemo {

    static volatile boolean done = false; //this variable not visible to different threads, volatile makes it visible

    public static void main(String[] args) {
        long niter = 1000L;
        log.info("nota bene: order between threads is non-deterministic");


        Runnable hellos = () -> {
            for (int i = 1; i <= niter; i++)
                System.out.println("Hello " + i);
            done = true;
        };
        Runnable goodbyes = () -> {
            for (int i = 1; i <= niter; i++) {
                System.out.println("Goodbye " + i);
            }
        };

        Runnable goodbyes_alt = () -> {
            int i = 1;
            while(!done) i++;
            System.out.println("Goodbye " + i);
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbyes_alt); //goodbyes

    }
}
