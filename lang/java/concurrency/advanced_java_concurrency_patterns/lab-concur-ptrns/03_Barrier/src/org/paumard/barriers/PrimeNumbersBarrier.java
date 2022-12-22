package org.paumard.barriers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbersBarrier {

    public static void main(String[] args) {
        final int nTasks = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(nTasks);

        CyclicBarrier barrier = new CyclicBarrier(nTasks, () -> System.out.println("Barrier openning"));

        //prp inputs
        List<List<Integer>>  inputs = new ArrayList<>();
        inputs.add(IntStream.range(0,99).boxed().collect(Collectors.toList()));
        inputs.add(IntStream.range(100,199).boxed().collect(Collectors.toList()));
        inputs.add(IntStream.range(200,299).boxed().collect(Collectors.toList()));
        inputs.add(IntStream.range(300,400).boxed().collect(Collectors.toList()));
        List<Integer> primeResult = new ArrayList<>();


        List<Future<List<Integer>>> futures = new ArrayList<>();

        try {
            for (int i = 0 ; i < nTasks ; i++) {
                PrimeNumbersBarrier.Worker worker = new PrimeNumbersBarrier.Worker(barrier, inputs.get(i));
                futures.add(executorService.submit(worker));
            }

            futures.forEach(
                    future -> {
                        try {
                             primeResult.addAll(future.get(10_000L, TimeUnit.MILLISECONDS));
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println(e.getMessage());
                        } catch (TimeoutException e) {
                            System.out.println("Timed out");
                            future.cancel(true);
                        }
                    }
            );

        } finally {
            executorService.shutdown();
        }
        //log
        System.out.println("result prime numbers: " + primeResult);
    }




   public static class Worker implements Callable<List<Integer>>{
        private CyclicBarrier barrier;
        private List<Integer> inputList;
        public  Worker(CyclicBarrier barrier, List<Integer> inputList){
            this.barrier = barrier;
            this.inputList = inputList;
        }

        public List<Integer> call(){
            List<Integer> result = findPrimes(inputList);
            try{
                barrier.await();
            }catch(InterruptedException | BrokenBarrierException e){
                System.out.println("Exception: " + e.getMessage());
            }
            return result;
        }

        private List<Integer> findPrimes(List<Integer> inputList){
            List<Integer> primers = new ArrayList<>();
            for(Integer v : inputList){
                if(isPrimeBruteForce(v.intValue())) { primers.add(v); }
            }
            return primers;
        }

       private boolean isPrimeBruteForce(int number){
           for(int i=2; i*i <= number;i++){
               if (number % i ==0){
                   return false;
               }
           }
           return true;
       }
   }

}
