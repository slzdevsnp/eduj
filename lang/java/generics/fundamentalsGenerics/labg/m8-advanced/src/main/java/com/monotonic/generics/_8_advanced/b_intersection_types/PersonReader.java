package com.monotonic.generics._8_advanced.b_intersection_types;

import com.monotonic.generics._8_advanced.Person;

import java.io.*;

public class PersonReader
{
    public static void main(String[] args) throws FileNotFoundException
    {
        PersonReader reader = new PersonReader();
       String fileAbspath="/home/zimine/repos/clearn/java8/fundamentals/fundamentalsGenerics/labg/m8-advanced/src/main/resources/person";
        DataInputStream stream = new DataInputStream(new FileInputStream(fileAbspath));
        Person person = reader.read(stream); //call method from below
        System.out.println("person read from inputStream: "+ person);

        RandomAccessFile randomAccessFile = new RandomAccessFile(fileAbspath, "rw");
        person = reader.read(randomAccessFile);
        System.out.println("person read from file: "+ person);
    }

    public <T extends DataInput & Closeable> Person read(final T source) {
        try(T input = source) {
            return new Person(input.readUTF(), input.readInt());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
