package org.slzdevsnp.optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class TestOptionalBaeldung {

    @BeforeEach
    void before(){}

    @AfterEach
    void after(){}

    @Test
    void testOptional_whenIfPresent(){
        Optional<String> opt = Optional.of("baeldung"); // .of(argument cannot be null)
        opt.ifPresent(x -> System.out.println("opt value:"+x + " opt.len:"+x.length()));
        //opt.isEmpty(); opt.isPresent()
        assertThat(opt.isEmpty()).isFalse();
    }

    @Test
    void testOptional_DefaultValue(){
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("john");
        assertThat(name).isEqualTo("john");
    }


    private String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:");

        // NB: method: getMyDefault() is not invoked at all.
        // no unnecessary object creation.
        String defaultText = Optional.ofNullable(text)
                            .orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);
    }

    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);
    }


}
