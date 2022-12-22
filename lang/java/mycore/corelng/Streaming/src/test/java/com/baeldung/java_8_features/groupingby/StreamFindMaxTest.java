package com.baeldung.java_8_features.groupingby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class StreamFindMaxTest {

    @Data
    @AllArgsConstructor
    @ToString
    class Person {
        String name;
        Integer age;
    }

    @Test
    public void whenListIsOfIntegerThenMaxCanBeDoneUsingIntegerComparator() {
        // given
        List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
        Integer expectedResult = 89;

        // then
        Integer maxValue = listOfIntegers
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        assertThat(maxValue, is(expectedResult));
    }

    @Test
    public void findMaxObjectField(){
        //given
        Person alex = new Person("Alex", 23);
        Person john = new Person("John", 40);
        Person peter = new Person("Peter", 32);
        List<Person> people = Arrays.asList(alex, john, peter);

        Person minByAge = people
                .stream()
                .min(Comparator.comparing(Person::getAge))
                .orElseThrow(NoSuchElementException::new);
        Person maxByAge = people
                .stream()
                .max(Comparator.comparing(Person::getAge))
                .orElseThrow(NoSuchElementException::new);

        //alternatively
        Integer maxByAgeAlt = people
                .stream()
                .mapToInt(v -> v.getAge())
                .max()
                .orElseThrow(NoSuchElementException::new);

        assertThat(minByAge, is(alex));
        assertThat(maxByAge, is(john));
        assertThat(maxByAgeAlt, is(40));
    }
}
