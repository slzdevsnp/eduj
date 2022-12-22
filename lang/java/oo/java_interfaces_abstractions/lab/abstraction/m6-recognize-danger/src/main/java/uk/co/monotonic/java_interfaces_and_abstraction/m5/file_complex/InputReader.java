package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_complex;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.util.Collections.emptyList;

public class InputReader
{
    private static final List<Extractor> EXTRACTORS = Arrays.asList(
        new TextExtractor(),
        new ZipExtractor(),
        new GzipExtractor());

    public static List<String> readFile(final String path) throws IOException
    {
        for (final Extractor extractor : EXTRACTORS)
        {
            if (path.endsWith(extractor.fileNameSuffix()))
            {
                final InputStream input = extractor.getInputStream(path);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
                {
                    return reader.lines().collect(Collectors.toList());
                }
            }
        }

        return emptyList();
    }
}
