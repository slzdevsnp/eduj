package concurrentHashMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path path) {
        try {
            String contents = new String(Files.readAllBytes(path),
                StandardCharsets.UTF_8);
            for (String word : contents.split("\\PL+")) {
                map.merge(word, 1L, Long::sum);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Set<Path> descendants(Path p) throws IOException {        
        try (Stream<Path> entries = Files.walk(p)) {
           return entries.filter(f -> f.toString().endsWith(".java")).collect(Collectors.toSet());
        }
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathToRoot = Paths.get("..");
        for (Path p : descendants(pathToRoot)) {
            executor.execute(() -> process(p));
        }        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println(map);
    }
}
