package org.slzdev;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;


import java.util.Arrays;
import java.util.List;


public class ReduceTest {

  private List<String> langNames;

  @Before
  public void init(){
   langNames = Arrays.asList("Java", "Python", "JavaScript");
  }

  @Test
  public  void printAllElems(){
      langNames.stream().forEach(System.out::println);
  }

  @Test
  public void testReduce(){
    String result = langNames.stream().reduce("",(a,b) -> a +" " + b);
      System.out.println("reduced result: " + result);
  }

}
