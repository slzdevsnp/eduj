package uk.co.monotonic.java_interfaces_and_abstraction.m5.file_complex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface Extractor
{
    String fileNameSuffix();

    InputStream getInputStream(String path) throws IOException;
}
