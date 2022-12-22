package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_complex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipExtractor implements Extractor
{
    @Override
    public String fileNameSuffix()
    {
        return "gz";
    }

    @Override
    public InputStream getInputStream(final String path) throws IOException
    {
        return new GZIPInputStream(new FileInputStream(path));
    }
}
