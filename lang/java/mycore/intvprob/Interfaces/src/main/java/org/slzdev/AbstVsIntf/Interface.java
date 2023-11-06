package org.slzdev.AbstVsIntf;

public interface Interface {

    void abstractMethod(); // Abstract method by default

    default void defaultMethod() { // Default method
        System.out.println("This is a default implemented method in an interface.");
    }
}
