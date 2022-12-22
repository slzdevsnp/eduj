package org.szi.l04Concurrent.threadLocal;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class ThreadLocalDemo {
    public static final NumberFormat sharedFormatter
            = NumberFormat.getCurrencyInstance(); // BAD
    public static final ThreadLocal<NumberFormat> currencyFormat
            = ThreadLocal.withInitial(() -> NumberFormat.getCurrencyInstance()); // GOOD

    public static String dollars_bad(double value) {
        NumberFormat formatter = sharedFormatter;
        // Try this to see what happens...
        return formatter.format(value);
    }

    public static String dollars_good(double value) {
        NumberFormat formatter = currencyFormat.get();
        return formatter.format(value);
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("with shared Formatter (bad)");
        test_bad_format();
        log.info("with ThreadLocal formatter (good)");
        test_good_format();
    }

    static  void test_bad_format() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            double value = 1000.01 * i;;
            tasks.add(() -> dollars_bad(value));
        }
        List<Future<String>> result = executor.invokeAll(tasks);
        for (Future<String> f : result)
            System.out.println(f.get());
        executor.shutdown();
    }
    static  void test_good_format() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            double value = 1000.01 * i;;
            tasks.add(() -> dollars_good(value));
        }
        List<Future<String>> result = executor.invokeAll(tasks);
        for (Future<String> f : result)
            System.out.println(f.get());
        executor.shutdown();
    }

}
