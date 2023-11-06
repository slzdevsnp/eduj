package org.slzdev.streaming;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ReduceTest {

    private List<String> langNames;

    @BeforeEach
    public void init(){
        langNames = Arrays.asList("Java", "Python", "JavaScript");
    }

    @Test
    public  void printAllElems(){
        langNames.stream().forEach(System.out::println);
        assertEquals(langNames.size(), 3);
    }

    @Test
    public void testReduce(){
        String result = langNames.stream().reduce("",(a,b) -> a +" " + b);
        System.out.println("reduced result: " + result);
        assertEquals(result, " Java Python JavaScript");
    }

}
