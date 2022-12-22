package lambdasstreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsingCollect {
    public static void main(String[] args) {
        System.out.println("#### stream 1");
        int x = 2;
        Stream.of(3, 1, 4, 1, 5, 9)
                .mapToInt( n -> n * x)
               //.mapToInt( n-> {x++; return x * n;}) // can't change x in lamda
                .forEach(System.out::println);


        List<String> wstrings = Arrays.asList(
                "this", "is", "a", "list", "of", "strings");

        System.out.println("#### stream 2");
        // Side effects --> not what we want
        List<String> evenLengths = new ArrayList<>();
        wstrings.stream()
                .filter(s -> s.length() % 2 == 0) // the list is modified
                .forEach(evenLengths::add); //modifying List inside stream
                                            //poor practice
        System.out.println(evenLengths);
        System.out.println("original list:"+wstrings);

        System.out.println("### stream 3");
        // No side-effects
        // desired pattern
        List<String> evens = wstrings.stream()
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList()); //produces new collection
        System.out.println(evens);
    }
}
