package org.slzdevsnp.resourceRead;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


public class TestResourceLoading {

    @Test
    public void testLoadResourceAsStream()  throws IOException{
        InputStream is= getClass()
                .getClassLoader()
                .getResourceAsStream("address.json");

        String content = readFromInputStream(is);
        System.out.println(content);
        assertTrue(content.length() > 0);
        assertTrue(is != null);
    }

    @Test
    public void testLoadResourceDeeperAsStream()  throws IOException{
        InputStream is= getClass()
                .getClassLoader()
                .getResourceAsStream("deeper/deep_address.json");
        String content = readFromInputStream(is);
        System.out.println(content);
        assertTrue(content.length() > 0);
        assertTrue(is != null);
    }

    @Test
    public void testLoadResourceAsPath()  throws IOException, URISyntaxException {

        Path path = Paths.get(getClass()
                .getClassLoader()
                .getResource("books.json").toURI());


        String content = new String(Files.readAllBytes(path));
        System.out.println(content);

        assertTrue(content.length() > 0);

    }

    @Test
    public void testLoadFile() throws IOException{
        Path path = Paths.get("src/test/resources/address.json");
        String content = new String(Files.readAllBytes(path));
        System.out.println(content);
        assertTrue(content.length() > 0);
    }

    @Test
    public void givenResource_getProperties() {
        Properties defaultProps = new Properties();
        try {
            defaultProps.load(TestResourceLoading.class.getResourceAsStream("/header_key.properties"));
        } catch (NullPointerException npe) {
            throw new IllegalStateException("Could not load application.properties file from classpath");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("props:"+defaultProps);
        assertTrue(defaultProps != null);
    }

    //helper method
    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
