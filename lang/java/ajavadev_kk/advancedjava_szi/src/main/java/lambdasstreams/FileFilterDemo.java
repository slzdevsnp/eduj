package lambdasstreams;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FileFilterDemo {
    public static void main(String[] args) {
        File jdirectory = new File("./src/main/java/lambdasstreams");
        System.out.println(jdirectory.getAbsolutePath());

        // Anonymous inner class
        Arrays.stream(jdirectory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("java");
            }
        })).forEach(System.out::println);

        // Use lambda expression instead
        Arrays.stream(
                jdirectory.list((dir, name) -> name.endsWith("java")))
                .forEach(System.out::println);

        // Assign lambda to variable
        FilenameFilter javaFiles = (dir, name) -> name.endsWith("java");
        Arrays.stream(
                jdirectory.list(javaFiles))
                .forEach(System.out::println);
    }
}
