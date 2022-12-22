package org.szi.l02Streams.transforming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.szi.l02Streams.creating.CreatingStreams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterMapDemo {
    private final static Logger log = LoggerFactory.getLogger(CreatingStreams.class) ;


    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            result.add(s.substring(i, i + 1));
        return result.stream();
    }
    public static void main(String[] args) throws IOException  {

        String contents = new String(Files.readAllBytes(Paths.get("/usr/share/dict/words")),
                StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));


        log.info("## map: to lowercase, first chars ");
        Stream<String> lowercaseWords = words.stream()
                                        .map(String::toLowerCase);
                                        //.map(x -> x.toLowerCase());
        show("lowecase words", lowercaseWords);
        show("first chars from words",
                words.stream()
                     .filter(w -> w.length() >0)
                     .map(s->s.substring(0,1)) );


        log.info("### flat map from a song:");
        String[] song = { "row", "row", "your", "boat", "gently", "down",
                "the", "stream" };
        Stream<String> letters_of_song_words = Stream.of(song)
                                              .flatMap(w -> letters(w));
        log.info("chars from the song: {}", letters_of_song_words.collect(Collectors.toList()));

        log.info("######  less used ops: limit, skip, concat, distinct, peek");
        lessUsedStreamOps();

    }

    static void lessUsedStreamOps(){
        log.info("### limit   bounded stream of numbers");
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        log.info("stream of randomes capped at {} elements", randoms.count());

        log.info("### skipping first elements");
        Stream<String> wordss  = Stream.of("one", "two", "three")
                .skip(1);
        show("words with first element skipped:", wordss);

        log.info("### concat 2 streams");
        Stream<String> combined = Stream.concat(Stream.of("a", "b"), Stream.of("c", "d"));
        show("concatenated stream:", combined );

        Stream<String> uniqueWords = Stream.of("aa", "bb", "aa", "dd", "cc", "cc", "ee")
                .distinct();
        show("stream of distict words",uniqueWords);

        log.info("##### sorted");
        String[] song = { "row", "row", "your", "boat", "gently", "down",
                "the", "stream" };
        Stream<String> longestFirst = Stream.of(song).sorted(Comparator.comparing(String::length).reversed());
        show("song sorted of words length", longestFirst);

        log.info("##### peek, useful for debugging");
        Object[] powers = Stream.iterate(2.0, p-> p*2)
                          .peek(e->System.out.println("fetching "+e))
                          .limit(10).toArray();

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
