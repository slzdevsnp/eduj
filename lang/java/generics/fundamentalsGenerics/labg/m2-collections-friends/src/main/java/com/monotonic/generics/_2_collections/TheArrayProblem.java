package com.monotonic.generics._2_collections;

import java.util.Arrays;

public class TheArrayProblem
{
    public static void main(String[] args)
    {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);

        Person[] madMen = { donDraper, peggyOlson };

        //pass Array to Strings of elements
        System.out.println(Arrays.toString(madMen));

        madMen = add(new Person("Bert Cooper", 100), madMen);

        System.out.println(Arrays.toString(madMen));

        madMen = add(peggyOlson, madMen);

        System.out.println(Arrays.toString(madMen));
    }

    //implementation how to add an element to Array
    private static Person[] add(Person person, Person[] array)
    {
        int length = array.length;
        //Arrays.copy  expensive? operation (boilerplate)
        Person[] newArray = Arrays.copyOf(array, length + 1);
        newArray[length] = person;
        return newArray;
    }
}
