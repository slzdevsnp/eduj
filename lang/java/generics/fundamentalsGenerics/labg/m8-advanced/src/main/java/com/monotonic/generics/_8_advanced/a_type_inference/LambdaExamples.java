package com.monotonic.generics._8_advanced.a_type_inference;

import com.monotonic.generics._8_advanced.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class LambdaExamples {
    public static void main(String[] args) {
        final Person donDraper = new Person("Don Draper", 89);
        final Person peggyOlson = new Person("Peggy Olson", 75);
        final Person bertCooper = new Person("Bert Cooper", 100);

        List<Person> people = new ArrayList<>();
        people.add(donDraper);
        people.add(peggyOlson);
        people.add(bertCooper);

        Predicate<Person> isOldFunc = p -> p.getAge() > 80;
        //<Boolean, Long> :  p.getAge()>80 returns Boolean,   counting() returns Long
        Map<Boolean, Long> oldAndYoungPeople =
                people.stream()
                        .collect(partitioningBy(p -> p.getAge() > 80,
                                counting()
                                )
                        );
        System.out.println("partition of counts predicate, negate(predicate): "
                + oldAndYoungPeople);

        List<Person> oldfolks = people.stream()
                .filter(isOldFunc)
                .collect(Collectors.toList());
        System.out.println("list of old people: " + oldfolks);

    }
}
