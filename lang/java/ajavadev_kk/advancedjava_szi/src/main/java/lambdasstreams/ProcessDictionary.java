package lambdasstreams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

public class ProcessDictionary {
    public static void main(String[] args) throws Exception {
        // in osx /usr/share/dict/web2
        // in linux /usr/share/dict/words
        System.out.println("### first stream");
        Files.lines(
                Paths.get("/", "usr", "share", "dict", "words"))
                .filter(s -> s.length() > 10)
                .map(String::toLowerCase)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(10)
                .forEach((String w) ->
                        System.out.printf("%s (%d)%n", w, w.length()));

        System.out.println("### 2nd stream");
        Optional<String> max = Files.lines(
                //Paths.get("/", "usr", "share", "dict", "words"))
                Paths.get("/usr/share/dict/words"))
                .filter(s -> s.length() > 10)
                .map(String::toLowerCase)
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst(); //only the first one

        System.out.println("lazy streams. computation kicks only now..");
        System.out.println(max.isPresent() ? max.get() : max.orElse("None found"));
        System.out.println(max.orElseGet(() -> "nothing"));
        System.out.println(max.orElse("nothing"));
    }
}
