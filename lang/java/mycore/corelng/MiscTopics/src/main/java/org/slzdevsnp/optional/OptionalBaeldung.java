package org.slzdevsnp.optional;

import java.util.Optional;

public class OptionalBaeldung {

    static void basicExample(){
        Optional<String> opt = Optional.of("baeldung");
        Optional<String> optAlt = Optional.empty();

        opt.ifPresent(x -> System.out.printf("opt contents:"+x));
        optAlt.ifPresent(x -> System.out.printf("opt contents:"+x));

    }

    public static void main(String[] args) {
        basicExample();
    }


}
