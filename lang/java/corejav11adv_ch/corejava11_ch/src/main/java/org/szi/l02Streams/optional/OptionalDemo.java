package org.szi.l02Streams.optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    private static final Logger log = LoggerFactory.getLogger(OptionalDemo.class);

    public static Optional<Double> inverse(Double x){
        return(x == 0) ? Optional.empty() : Optional.of(1/x);
    }

    public static Optional<Double> squareRoot(Double x){
        return (x < 0) ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    public static void main(String[] args) throws IOException {

        log.info("### basic optional");
        Optional<String > optionalString = Optional.empty();
        //Optional<String> optionalString = Optional.ofNullable(null);
        String result = optionalString.orElse("N/A");
        //String result = optionalString.orElseGet(() -> System.getProperty("user.dir"));
        //String result = optionalString.orElseThrow(IllegalStateException::new);
        log.info("this string from optional  can never be null: {}",result);

        log.info("#### alice");
        String contents = new String(Files.readAllBytes(Paths.get("src/main/resources/alice.txt")),
                StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream().filter(s -> s.contains("fred"))
                .findFirst();
        log.info("optionally check if wordlis has a filtered content: {}"
                ,optionalValue.orElse("No specified value"));

        optionalValue = wordList.stream().filter(s -> s.contains("red")).findFirst();
        optionalValue.ifPresent(s->log.info("{} contains read",s));

        log.info("#### inverse and square root");
        //chaining methods on optional
        log.info("inverse then root for {} :{}", 4.0
                , inverse(4.0).flatMap(OptionalDemo::squareRoot));
        log.info("inverse then root for {} :{}", -2.0
                , inverse(-2.0).flatMap(OptionalDemo::squareRoot));
        log.info("inverse then root for {} :{}", 0.0
                , inverse(0.0).flatMap(OptionalDemo::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0).flatMap(OptionalDemo::inverse)
                .flatMap(OptionalDemo::squareRoot);
        log.info(String.valueOf(result2));


    }
}
