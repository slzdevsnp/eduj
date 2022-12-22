package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_complex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TextExtractor implements Extractor
{
    @Override
    public String fileNameSuffix()
    {
        return "txt";
    }

    @Override
    public InputStream getInputStream(final String path) throws FileNotFoundException
    {
        return new FileInputStream(path);
    }
}
