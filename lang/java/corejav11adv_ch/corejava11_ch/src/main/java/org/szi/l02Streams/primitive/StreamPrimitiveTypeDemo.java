package org.szi.l02Streams.primitive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrimitiveTypeDemo {
private static final Logger log = LoggerFactory.getLogger(StreamPrimitiveTypeDemo.class);

    public static void main(String[] args) throws IOException {

        log.info("### IntStream");
        IntStream istream0 = IntStream.of(1,1,2,3,5);
        int[] iarr = {10,11,12,13,14};
        IntStream istream1 = Arrays.stream(iarr);
        log.info("istream1:{}", istream1.toArray());

        log.info("#### Range");
        log.info("stream as range {}", IntStream.range(10,20).toArray());

        Stream<String> words = getWords("src/main/resources/alice.txt");
        IntStream lenghts = words.mapToInt(String::length);
        log.info("lenght of first 10 words: {}", lenghts.limit(10).toArray());

        log.info("### to wrapper Stream");
        Stream<Integer> integers = IntStream.range(0,100).boxed();
        log.info("first 10 els of boxed stream {}",
                integers.limit(10).collect(Collectors.toList()));

        log.info("int stream: {}",IntStream.range(0,10)
                .map(n-> n*n)
                .toArray());
        log.info("### Random");
        log.info("random array {}", new Random().ints().limit(15).toArray());

        log.info("### unicode");
        String hello = "Hello \uD83D\uDE3B";
        log.info(hello);
        log.info("hello through stream:{}", Arrays.toString(hello.codePoints().toArray()));

        log.info("### max, summaryStatistics");
        log.info("max val: {}", new Random().ints().map(n->n % 100).limit(1000).max());
        IntSummaryStatistics istats = new Random().ints()
                        .map(n -> n % 100)
                        .limit(1000)
                        .summaryStatistics();
        log.info("summary stats: {}",istats);
    }



    public static Stream<String> getWords(String filename) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(filename)),
                StandardCharsets.UTF_8);
        return Pattern.compile("\\PL+").splitAsStream(contents);
    }

}
