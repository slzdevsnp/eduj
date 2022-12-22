package com.monotonic.generics._4_methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingExamples
{
    public static void main(String[] args)
    {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 100);

        List<Person> madMen = new ArrayList<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        madMen.add(bertCooper);

        final Person youngestCastMember = min(madMen, new AgeComparator());

        System.out.println("youngest found: " + youngestCastMember);

        //example on buil-int numeric type comparator
        List<Integer> numbrs = new ArrayList<>();
        numbrs.add(7);
        numbrs.add(9);
        numbrs.add(3);
        Integer minNumbr = min(numbrs, new Comparator<Integer>() { //with anon class
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("min number found: " + minNumbr );

        Integer minNumbrUsual = Collections.min(numbrs);
        System.out.println("min number found using collections : " + minNumbrUsual);


    }

    public static <T> T min(List<T> values, Comparator<T> comparator)
    {
        if (values.isEmpty())
        {
            throw new IllegalArgumentException("Unable to find the minimum of an empty list");
        }

        T lowestFound = values.get(0);

        for (int i = 1; i < values.size(); i++)
        {
            final T element = values.get(i);
            if (comparator.compare(element, lowestFound) < 0)
            {
                lowestFound = element;
            }
        }

        return lowestFound;
    }

}
