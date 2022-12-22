package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_complex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipExtractor implements Extractor
{
    @Override
    public String fileNameSuffix()
    {
        return "zip";
    }

    @Override
    public InputStream getInputStream(final String path) throws IOException
    {
        final ZipFile zipFile = new ZipFile(path);
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        final ZipEntry entry = entries.nextElement();
        return zipFile.getInputStream(entry);
    }
}
