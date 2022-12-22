package org.szi.l02Streams.parallel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ParallelStreams {
public static final Logger log = LoggerFactory.getLogger(ParallelStreams.class);

    public static double time(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) * 1E-9;
    }

    public static Stream<String> getWords(String filename) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(filename)),
                StandardCharsets.UTF_8);
        return Pattern.compile("\\PL+").splitAsStream(contents);
    }

    public static Stream<String> getLines(String fspath) throws IOException {
        Stream<String>  lines = Files.lines(Paths.get(fspath));
        return lines;
    }

    public static void main(String[] args) throws IOException {

        List<String> words = getWords("src/main/resources/alice.txt")
                .collect(Collectors.toList());

        log.info("### right code for short words");
        //remedy : group and count
        Map<Integer, Long> shortWordCounts =
                words.parallelStream()
                        .filter(s -> s.length() < 10)
                        .collect(
                                groupingBy(
                                        String::length,
                                        counting()));
        log.info("short words counts:{}",shortWordCounts);

        Map<Integer,Long> shortWordCountsAlt =
                getLines("/usr/share/dict/words")
                .parallel()
                .filter(s -> s.length() < 10)
                        .collect(
                                groupingBy(
                                        String::length,
                                        counting()));
        log.info("short words counts dict words:{}",shortWordCountsAlt);


        log.info("### parallel compared to non parallel");
        log.info("without parallel ");
        long start = 1_000_000_000L;
        long end = 1_005_000_000L;
        System.out.println("Seconds: " + time( () ->
                System.out.println("Primes: " +
                        LongStream.range(start, end)
                                .filter(n -> BigInteger.valueOf(n).isProbablePrime(100))
                                .count())));

        log.info("With parallel");
        System.out.println("Seconds: " + time(() ->
                System.out.println("Primes: " +
                        LongStream.range(start, end)
                                .parallel()
                                .filter(n -> BigInteger.valueOf(n).isProbablePrime(100))
                                .count())));

    }
}
