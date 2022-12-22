package packt.j9fprog.fmapping;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import packt.j9fprog.genericMethods.model.LibraryMember;

public class ComplexFunctional {
    public static void main(String args[]) {
        System.out.println("################## Flatmap 1 cities #####################");
        flatmapCities();
        System.out.println("################## Flatmap 2 books #####################");
        flatMapMemberBooks();

        //Reduce
        System.out.println("################## Sum Reduce #####################");
        sumReduce();

        //Count
        System.out.println("################## Sum Reduce #####################");
        countIntegers();
    }

    public static void countIntegers() {
        //built in operations
        long count = IntStream.range(1, 100).count();
        long sum = IntStream.range(1, 100).sum();
        System.out.println("Total values between 1 and 100 is " + count);
        System.out.println("Sum of values between 1 and 100 is " + sum);
    }

    private static void sumReduce() {
        //inner class
        class Person {
            private final int age;

            public Person(int age) {
                this.age = age;
            }

            public int getAge() {
                return this.age;
            }
        }

        List<Person> people = new ArrayList<>();
        people.add(new Person(10));
        people.add(new Person(16));
        people.add(new Person(25));
        people.add(new Person(65));
        people.add(new Person(21));
        people.add(new Person(94));
        people.add(new Person(25));

        int totalAge = people.stream()
                .map(person -> person.getAge())
                .reduce((a, b) -> a + b)
                .get();

        int alsoTotalAge = people.stream().mapToInt(person -> person.getAge()).sum();

        System.out.println("Total age is " + totalAge);
        System.out.println("also total age:"+alsoTotalAge);
    }

    private static void flatmapCities() {
        List<String> UKCities = Arrays.asList("London", "Manchester", "Birmingham", "Glasgow", "Cardiff");
        List<String> USCities = Arrays.asList("New York", "Washington DC", "LA", "Miami", "Atlanta");
        List<String> EUCities = Arrays.asList("Paris", "Brussels", "Munich", "Berlin", "Madrid");
        List<String> AsianCities = Arrays.asList("Beijing", "Singapore", "Hong Kong", "Jakarta", "Tokyo");

        List<List<String>> allTheCities = new ArrayList<>(); // List of Lists
        allTheCities.add(UKCities);
        allTheCities.add(USCities);
        allTheCities.add(EUCities);
        allTheCities.add(AsianCities);

        //I want to combine all the lists
        allTheCities.stream()
                .flatMap(cityList -> cityList.stream())
                .forEach(city -> System.out.println(city));
    }

    private static void flatMapMemberBooks() {
        LibraryMember member = new LibraryMember();
        member.addBook("Java 8 in Action");
        member.addBook("Spring Boot in Action");
        member.addBook("Effective Java (2nd Edition)");

        LibraryMember member2 = new LibraryMember();
        member2.addBook("Learning Python, 5th Edition");
        member2.addBook("Effective Java (2nd Edition)");
        member2.addBook("Spring Boot in Action");

        List<LibraryMember> membersList = new ArrayList<>();
        membersList.add(member);
        membersList.add(member2);

        List<String> collect =
                membersList.stream()
                        .map(x -> x.getBooksOnLoan())      //Stream<Set<String>>
                        .flatMap(x -> x.stream())   //Combines all the streams into one
                        .distinct()
                        .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x));
    }
}

