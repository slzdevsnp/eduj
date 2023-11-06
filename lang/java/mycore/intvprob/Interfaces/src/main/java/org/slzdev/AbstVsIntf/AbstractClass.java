package org.slzdev.AbstVsIntf;

public abstract class AbstractClass {


    abstract void abstractMethod(); // Abstract method needs to be implemented in children classes

    void nonAbstractMethod() { // Non-abstract method
        System.out.println("This is a non-abstract method in an abstract class.");
    }
}
