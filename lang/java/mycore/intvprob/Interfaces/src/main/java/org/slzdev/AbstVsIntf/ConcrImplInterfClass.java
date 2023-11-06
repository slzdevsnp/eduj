package org.slzdev.AbstVsIntf;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcrImplInterfClass implements Interface{

    @Override
    public void abstractMethod() {
        System.out.println("implemented abstract method from an interface in a concrete class");
    }
}
