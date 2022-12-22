package org.szi.l02Streams.transforming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordsDictionaryDemo {
    final static Logger log = LoggerFactory.getLogger(WordsDictionaryDemo.class);

    Stream<String> getWords(String fspath) throws IOException {
        Stream<String>  lines = Files.lines(Paths.get(fspath));
        return lines;
    }
    public static void main(String[] args) throws IOException{


        log.info("### counting filtered words..");
        WordsDictionaryDemo wd = new WordsDictionaryDemo();
        String fpath="/usr/share/dict/words";
        Stream<String> words = wd.getWords(fpath); //linux

        log.info(" words with lenght > 12: {} out of {} total words",
                words
                .filter(w -> w.length() > 12)
                .count(),
                wd.getWords(fpath).count());

        //get list of lenght words
        Stream<String> words_again = wd.getWords(fpath);
        List<String> lenghthy_words = words_again
                                        .filter(w -> w.length() > 12)
                                        .collect(Collectors.toList());
        log.info("list of lenghthy words count: {}, first element: {}", lenghthy_words.size(), lenghthy_words.get(0));
    }



}
