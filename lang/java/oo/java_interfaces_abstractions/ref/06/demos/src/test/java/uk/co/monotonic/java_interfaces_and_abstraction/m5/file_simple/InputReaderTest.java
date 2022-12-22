package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_simple;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class InputReaderTest
{
    @Test
    public void readsTextFiles() throws IOException
    {
        assertReadsFile("example.txt");
    }

    @Test
    public void readsZipFiles() throws IOException
    {
        assertReadsFile("example.zip");
    }

    @Test
    public void readsGZipFiles() throws IOException
    {
        assertReadsFile("example.txt.gz");
    }

    private void assertReadsFile(final String fileName) throws IOException
    {
        final List<String> contents = InputReader.readFile("src/main/resources/" + fileName);
        assertEquals(asList("Hello World"), contents);
    }

}