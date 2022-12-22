package org.szi.l04Concurrent.callable;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CallableDemo {

    // this method is used in a callable task
    /**
     * Counts occurrences of a given word in a file.
     * @param word the word to search
     * @param path the file in which to search
     * @return the number of times the word occurs in the given word.
     */
    public static long occurrences(String word, Path path) {
        try {
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            return Pattern.compile("\\PL+")
                    .splitAsStream(contents)
                    .filter(Predicate.isEqual(word))
                    .count();    // counts of word in a particular file path
        } catch (IOException ex) {
            return 0;
        }
    }

    // this method is used in a callable task
    /**
     * Yields all descendants of a directory
     * @param p the path to the directory
     * @return all files that are in the given directory or one of its descendants
     * @throws IOException
     */
    public static Set<Path> descendants(Path p) throws IOException {
        try (Stream<Path> entries = Files.walk(p)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String word = "String";
        Set<Path> paths = descendants(Paths.get(".."));  //find all file paths below dir ".."
        log.info("found {} files in paths",paths.size());

        List<Callable<Long>> tasks = new ArrayList<>();  //define a list of Callable tasks
        for (Path p : paths) tasks.add(
                () -> { return occurrences(word, p); });

        int processors = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executor = Executors.newFixedThreadPool(processors);  //instantiate an executor service
        log.info("number of processors used: {}",processors);

        List<Future<Long>> results = executor.invokeAll(tasks);   //fire all tasks in ArrayList  for concurrent execution

        long total = 0;
        for (Future<Long> result : results) total += result.get();  //process the result of all tasks
        System.out.println("Occurrences of " + word + ": " + total);

        //part II
        String searchWord = "filter"; //filter
        List<Callable<Path>> searchTasks = new ArrayList<>();
        for (Path p : paths) searchTasks.add(
                () -> { System.out.println("Searching in " + p);
                    if (occurrences(searchWord, p) > 0) return p;
                    else{
                        System.out.println("about to throw runtime exception");
                        throw new RuntimeException("");
                    }
                });
        Path found = executor.invokeAny(searchTasks); //will retun a result of a first task that did not throw an exception
        System.out.println(searchWord + " occurs in: " + found);

        executor.shutdown();
    }
}
