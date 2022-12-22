package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Arithmetic {
    public static void main(String[] args) {
        int x = 3;
        int y = 2 ; //2
        double z = 0;
        try {
            z = x / y;
        } catch (ArithmeticException e) {
            System.err.println(e);
        } finally {
            System.out.println("This gets called in any case in a finally block");
        }
        System.out.printf("x = %d, y = %d, z = %s%n", x, y, z);

        try {
            Arithmetic.class.newInstance(); //instantiates a class if it has a default constructor
        } catch (InstantiationException | IllegalAccessException e) { //way to catch > 1 exceptions
            e.printStackTrace();
        }

        // **try with resources**
        Path dir = Paths.get("src", "main", "java", "exceptions");

        //all these resources will be closed outside the try
        try (BufferedReader br = Files.newBufferedReader(dir.resolve("Arithmetic.java"))) {
            System.out.println(br.readLine()); //prints first line
        } catch (IOException e) {
            e.printStackTrace();
        }

        //throwing an exception
        try {
            throw new MyException();
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

    }
}
