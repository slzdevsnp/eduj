package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_simple;

import java.io.*;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.util.Collections.emptyList;

public class InputReader
{
    public static List<String> readFile(final String path) throws IOException
    {
        final InputStream input;

        if (path.endsWith("txt"))
        {
            input = new FileInputStream(path);
        }
        else if (path.endsWith("zip"))
        {
            input = readZipFile(path);
        }
        else if (path.endsWith("gz"))
        {
            input = readGzipFile(path);
        }
        else
        {
            return emptyList();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
        {
            return reader.lines().collect(Collectors.toList());
        }
    }

    private static GZIPInputStream readGzipFile(final String path) throws IOException
    {
        return new GZIPInputStream(new FileInputStream(path));
    }

    private static InputStream readZipFile(final String path) throws IOException
    {
        final ZipFile zipFile = new ZipFile(path);
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        final ZipEntry entry = entries.nextElement();
        return zipFile.getInputStream(entry);
    }
}
