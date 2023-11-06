package org.slzdev.IntfDefaultMethod;

public interface Vehicle {
    void drive(); // regular method, which was implemented some tiem agoe

    default void honk() { // new method with default implementation
        System.out.println("Vehicle is honking!");
    }
}
