package com.monotonic.generics._8_advanced.c_safe_varargs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafeVarargsExample
{
    public static void main(String[] args)
    {
        //this snippet ok
        integerArrayOf();
        //this snippet ok
        objectPair();
        //this snippet runtime exception
        //integerPair();

        //safevargards example
        safeVarargsExample();
    }

    static void integerArrayOf(){
        Integer[] integers = arrayOf(1, 2);
        System.out.println(Arrays.toString(integers));
        System.out.println(integers.getClass());
    }
    static void objectPair(){
        Object[] strs = pair("a");
        System.out.println(Arrays.toString(strs));
        System.out.println(strs.getClass());
    }
    static void integerPair(){
        Integer[] pair = pair(1);
        System.out.println(Arrays.toString(pair));
        System.out.println(pair.getClass());

    }
    static void safeVarargsExample(){
        List<Integer> il = Arrays.asList(1,2);
        List<Double>  dl = Arrays.asList(21.99, 23.13);
        List<Number>  nl = combine(il,dl);
        nl.stream().forEach(e -> System.out.println("combined list el: " +e));
    }

    private static <T> T[] pair(T t) {
        return arrayOf(t, t); //NB! unsafe generics varargs
    }

    // THIS IS NOT SAFE CODE!
    private static <T> T[] arrayOf(T ... values) {
        return values;
    }

    @SafeVarargs
    private static <T> List<T> combine(List<? extends T> ... lists)
    {
        List<T> combined = new ArrayList<>();
        for (List<? extends T> list : lists)
        {
            combined.addAll(list);
        }
        return combined;
    }
}
