package org.slzdev.IntfDefaultMethod;

public class F1Car implements Vehicle{

    @Override
    public void drive() {
        System.out.println("this car is driving real fast");
    }

    @Override
    public void honk() {
        System.out.println("this car is honking very loudly");
    }
}
