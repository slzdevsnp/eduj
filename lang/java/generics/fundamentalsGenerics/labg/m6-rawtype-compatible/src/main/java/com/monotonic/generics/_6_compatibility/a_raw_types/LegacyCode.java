package com.monotonic.generics._6_compatibility.a_raw_types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LegacyCode {
    public static void main(String[] args) {

        List values = new ArrayList();  //raw type
        //List<Object> values = new ArrayList();
        values.add("abc");
        values.add(1);
        values.add(new Object());
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println("raw element: " + element);
        }


        List<Integer> generic = new ArrayList<>();
        generic.add(1);
        generic.add(2);
        generic.add(3);
        assignGenericTypeToRawTypeSafe(generic);

        // cann not cast object to another type
        assignRawTypeToTypedCollectionUnsafe(values); //produces runtime exception

    }

    private static void assignRawTypeToTypedCollectionUnsafe(List raw) {
        List<String> generic;
        generic = raw;
        for (String el : generic) {  //this will produce runtime exception
            System.out.println("element in assigned: " + el);
        }
    }

    private static<T> void assignGenericTypeToRawTypeSafe(List<T> genericList) {
        List raw = genericList;
        Iterator iterator = raw.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println("raw assigned el: " + element);
        }
    }

}
