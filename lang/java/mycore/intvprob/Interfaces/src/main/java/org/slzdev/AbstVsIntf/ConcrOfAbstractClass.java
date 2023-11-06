package org.slzdev.AbstVsIntf;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcrOfAbstractClass extends  AbstractClass  {

    @Override
    void abstractMethod() {
        log.info("this is imple of abstractMethod in child class ");
    }
}
