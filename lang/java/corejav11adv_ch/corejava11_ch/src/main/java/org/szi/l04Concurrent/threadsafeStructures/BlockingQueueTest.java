package org.szi.l04Concurrent.threadsafeStructures;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

@Slf4j
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10; //limiting size of queue
    private static final int SEARCH_THREADS = 100;  //making
    private static final Path DUMMY = Paths.get("DUMMY");

    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    private static int threadId;
    public static void main(String[] args) throws InterruptedException {
        Path directory = Paths.get("..");
        String keyword = "static";
        //task
        Runnable enumeratorProducer = () -> {
            try {
                enumerate(directory);
                queue.put(DUMMY);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(SEARCH_THREADS + 1); //101 threads
        executor.execute(enumeratorProducer);

        for (int i = 1; i <= SEARCH_THREADS; i++) {
            threadId = i;
            //task
            Runnable searcherConsumer = () -> {
                try {
                    boolean done = false;
                    while (!done) {
                        Path file = queue.take();
                        if (file == DUMMY) {
                            queue.put(file);
                            done = true;
                        }
                        else search(file, keyword, threadId);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                }
            };
            executor.execute(searcherConsumer);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

    }


    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * @param directory the directory in which to start
     * @throws IOException
     */
    public static void enumerate(Path directory) throws InterruptedException, IOException
    {
        try (Stream<Path> entries = Files.walk(directory)) {
            entries.filter(f -> f.toString().endsWith(".java")).forEach(p -> {
                try {
                    queue.put(p);
                } catch (InterruptedException ex) {
                }
            });
        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     * @param keyword the keyword to search for
     */
    public static void search(Path file, String keyword, int threadId) throws IOException {
        try (Scanner in = new Scanner(file, "UTF-8")) {
            in.useDelimiter("\\PL+");
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    System.out.printf("thread_id:%d %s:%d:%s%n", threadId, file, lineNumber, line);
            }
        }
    }
}
