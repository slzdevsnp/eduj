package org.slzdev.generics;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slzdev.generics.model.Person;
import org.slzdev.generics.model.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class GenericClassTypeTest {

    @Test
    public void givenListCheckClassType() {
      checkObjectType(  Arrays.asList(Integer.valueOf(10)) , Integer.class );
      checkObjectType( Arrays.asList(Double.valueOf(10.0)) , Double.class);
      checkObjectType( Arrays.asList(BigDecimal.valueOf(10.0003)), BigDecimal.class);
      //checkObjectType (String.valueOf(10)); // cannot specify
    }

    private <T extends Number>  void checkObjectType(List<T> obj, Class<T> elementClass){

        if (elementClass.equals(Integer.class)){
            log.debug(" T is Integer:..");
        }
        if (elementClass.equals(Double.class)){
             log.debug(" T is Double:..");
        }
        if (elementClass.equals(BigDecimal.class)){
            log.debug(" T is BigDecimal:..");
        }
    }


    @Test
    public void shouldInsertRelatedTypes() {
        List<String> names = Arrays.asList("micha", "Kolya");
        List<? super Person> pups = makePersonList(names, Person.class);
        pups.stream().forEach(x -> log.debug("element - {}",x.toString()));

        //does not really work
        List<? super Person> studs = makePersonList(names, Student.class);
        studs.stream().forEach(x -> log.debug("element - {}",x.toString()));


    }

    List<? super Person> makePersonList(List<String> names, Class<?> typeClass) {
       List<? super Person> tlist = new ArrayList<>();
       if (typeClass.equals(Person.class)) {
           names.stream()
                   .forEach(x -> {
                       Person p = Person.builder().name(x).age(10).build();
                       tlist.add(p);
                   });
       }
       if (typeClass.equals(Student.class)) {
           names.stream()
                   .forEach(x -> {
                       Student s = new Student(x, 22,"Bologna");
                       tlist.add(s);
                   });
       }
       return tlist;
   }
}
