package org.szi.l02Streams.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams {
private final static Logger log = LoggerFactory.getLogger(CreatingStreams.class) ;

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("src/main/resources/alice.txt");
        //3852 lines of text
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        log.info("### stream from reading a text file");
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);

        log.info("### basic stream from array");
        Stream<String> song =  Stream.of("gently", "down", "the", "road");
        show("song", song);

        log.info("### empty stream");
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        log.info("## unbounded stream of a word");
        Stream<String> echos = Stream.generate(() -> "Echo"); //infinite lazily initialized
        show("echos", echos);
        //log.info("cont of echos:{}",Stream.generate(()->"Echo").count());


        log.info("## unbounded stream of a random number");
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        log.info("## unbounded stream of natural numbers sequence");
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        log.info("## bounded stream of natural numbers sequence");
        BigInteger limit = new BigInteger(String.valueOf(1000000));
        Stream<BigInteger> bintegers = Stream.iterate(BigInteger.ZERO,
                                               n -> n.compareTo(limit) < 0,
                                               n -> n.add(BigInteger.ONE));
        //show("bounded integers", bintegers); [0,1,2,... ]
        log.info("bounded integers size: {}", bintegers.count());


        log.info("## words in another way");
        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);


    }


    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        //first 10 elements in stream
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());

        System.out.print(title + ": ");
        if (firstElements.size() <= SIZE)
            System.out.println(firstElements);
        else {
            firstElements.remove(SIZE); //removed 11th element
            String out = firstElements.toString();
            System.out.println(out.substring(0, out.length() - 1) + ", ...]");
        }
    }


}
