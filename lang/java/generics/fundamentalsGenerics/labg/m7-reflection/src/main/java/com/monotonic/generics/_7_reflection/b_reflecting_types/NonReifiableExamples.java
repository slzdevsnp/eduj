package com.monotonic.generics._7_reflection.b_reflecting_types;

import java.util.ArrayList;
import java.util.List;

public class NonReifiableExamples<T>
{
    public static void main(String[] args)
    {
        new NonReifiableExamples<String>().main();
    }

    private void main()
    {
        // System.out.println(T.class);

        List<String> strings = new ArrayList<>();
        System.out.println("strings " + strings.getClass());
        List<Integer> integers = new ArrayList<>();

        List raw = new ArrayList();

        System.out.println(strings.getClass() == integers.getClass()); //can't destinguish
        System.out.println(raw.getClass() == integers.getClass()); //can't destinguish

        List<? extends Number> numbers = new ArrayList<>();
        System.out.println("numbers: "+  numbers.getClass());
        System.out.println(numbers.getClass() == integers.getClass()); //can't destinguish
    }
}
