package org.szi.l02Streams.collecting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CollectingResults {
    private final static Logger log = LoggerFactory.getLogger(CollectingResults.class) ;

    public static Stream<String> words(String filename) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(filename)),
                StandardCharsets.UTF_8);
        return Pattern.compile("\\PL+").splitAsStream(contents);
    }

    public static <T> void show(String label, Collection<T> coll) {
        log.info(label + ": " + coll.getClass().getName());
        log.info("["
                + coll.stream()
                .limit(10)
                .map(Object::toString)
                .collect(Collectors.joining(", "))
                + "]");
    }

    public static void main(String[] args) throws IOException{

        log.info("### iterator");
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext()) log.info(iter.next() + " ");

        log.info("#### toArray");
        Object[] numbers = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toArray();
        log.info("object[] array (exp 1st el):{} ",numbers); // Note it's an Object[] array
        log.info("stream to Array to String: {}",Arrays.toString(numbers));

        log.info("#### to Integer Array");
        Integer[] numbers2 = Stream.iterate(0, n -> n + 1)
                .limit(10)
                .toArray(Integer[]::new);
        log.info(numbers2.toString()); // Note it's an Integer[] array
        log.info(Arrays.toString(numbers2));

        log.info("### collect");
        show("List", words("src/main/resources/alice.txt")
                            .collect(Collectors.toList()));
        show("Set", words("src/main/resources/alice.txt")
                        .collect(Collectors.toSet()));
        TreeSet<String> treeSet = words("src/main/resources/alice.txt")
                .collect(Collectors.toCollection(TreeSet::new));
        show("TreeSet", treeSet);

        log.info("#### summarizingInt");
        IntSummaryStatistics summary = words("src/main/resources/alice.txt")
                .collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        log.info("Average word length: {} with max word length:{}"
                ,averageWordLength,maxWordLength);

        log.info ("#### forEach");
        words("src/main/resources/alice.txt")
                .filter(s -> s.length() > 0)
                .limit(10)
                .forEach(log::info);

        log.info("### simple reductions");
        BigInteger limit = new BigInteger("1000");
        Stream.iterate(BigInteger.ZERO,
                n -> n.compareTo(limit) < 0,
                n -> n.add(BigInteger.ONE)).max(BigInteger::compareTo)
                .ifPresent(s -> log.info("biggest element in stream: {}",s)) ;

        Stream<String> words =  Stream.of("gently", "down", "the", "road");
        Optional<String> startWithQ = words
                .filter(s -> s.startsWith("Q"))
                .findFirst();
        startWithQ.ifPresent((v) -> log.info("first element started with Q:{}",v));

        log.info("do we have words.starting with q? {}",
                Stream.of("gently", "down", "the", "road")
                        .anyMatch(s->s.startsWith("Q")));

    }
}
