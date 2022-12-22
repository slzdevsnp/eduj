package com.monotonic.generics._6_compatibility.c_implications_of_erasure;

import org.graalvm.compiler.java.GraphBuilderPhase;

public class InstanceOf<T>
{
    public boolean equals(Object o)
    {
        // Banned
        if (o instanceof InstanceOf/*<T>*/) // instanceof InstanceOf<T> syntax is banned
        {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
         System.out.println(new InstanceOf<>() instanceof  InstanceOf);  //ok runs
         //System.out.println(new InstanceOf<Integer>() instanceof Integer); //compile error
         System.out.println(new InstanceOf<>() instanceof Object); //ok runs

    }

}
