package org.paumard.barriers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrimeNumbers {

    public static void main(String[] args) {

        //testSieveFromZero();
        testSinglePrimeNumber();
        testStartElements();

    }

    static void testSieveFromZero(){
        List<Integer> primesOne = sieveOfEratosthenes(25);
        List<Integer> primesTwo = sieveOfEratosthenes(50);

        System.out.println("from zero primes one " + primesOne);
        System.out.println("from zero primes two: " + primesTwo);
    }


    static void testSinglePrimeNumber(){
        System.out.println( "0 is prime: " + isPrimeBruteForce(0));
        System.out.println( "1 is prime: " + isPrimeBruteForce(1));
        System.out.println( "2 is prime: " + isPrimeBruteForce(2));
        System.out.println( "6 is prime: " + isPrimeBruteForce(6));
        System.out.println( "11 is prime: " + isPrimeBruteForce(11));
        System.out.println( "50 is prime: " + isPrimeBruteForce(50));

    }

    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static boolean isPrimeBruteForce(int number){
        for(int i=2; i*i <= number;i++){
            if (number % i ==0){
                return false;
            }
        }
        return true;
    }

    public static int getBucketSize(int maxElements, int nbuckets){
        int bucketSize = maxElements / nbuckets ;
        while (maxElements % nbuckets != 0){
            maxElements+=1;
            bucketSize = maxElements / nbuckets;
        }
        return bucketSize;
    }
    public static int getStartElement(int maxElements, int nbuckets, int bucketPosition){
        int bucketSize = getBucketSize(maxElements,nbuckets);
        int startElement = bucketPosition *  bucketSize;
        return startElement;
    }
    public static int getEndElement(int maxElements, int nbuckets, int bucketPosition){
        int bucketSize = getBucketSize(maxElements,nbuckets);
        int endElement = bucketPosition *  bucketSize + getBucketSize(maxElements,nbuckets);
        if (endElement > maxElements){ endElement = maxElements;}
        return endElement;
    }

    public static void testStartElements(){
        int maxEls =  1000;
        int nbuckets = 4;
        for (int i = 0 ; i<nbuckets; i++){
            System.out.println("bucket " + i + " start: " + getStartElement(maxEls,nbuckets,i)
                    + " end: " +  getEndElement(maxEls,nbuckets,i) );
        }
    }


}
