package org.slzdevsnp.io;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestFileReadWrite {

    @Test
    void givenFileModifiedWithinPeriodRecreate() throws IOException {
        char SEPARATOR = ',';
        String fname="/tmp/config.csv";
        File file = new File(fname);

        boolean isModifiedWithinWindow = isFileModifiedWithinSecond(fname,50);
        FileWriter fileWriter = new FileWriter(fname, isModifiedWithinWindow);
        String[] params = {"0","ccdd"};
        writeLine(fileWriter,params,SEPARATOR);
        fileWriter.flush();
        fileWriter.close();

        assertThat(file.exists(), is(true));

        System.out.println("abs path: " + file.getAbsolutePath());
        System.out.println(" file size " +file.length() );
    }

    @Test
    void shouldWriteStringToFile() throws IOException {
        String contents = "mama myla syna\npapa kushal kashu\n";
        File file = File.createTempFile("szi", ".dat");
        FileWriter writer = new FileWriter(file);
        writer.write(contents);
        writer.close();
        System.out.println("file canonical path: " + file.getCanonicalPath());
        assertThat(file.exists(), is(true));
        assertThat(file.length(), greaterThan(0L));
        //file.deleteOnExit(); //uncomment to check file contents  delete on jvm exit , test termination
    }

    @Test
    void givenFileWriteAndDeleteIt() throws IOException {
        char SEPARATOR = ',';
        String fname="/tmp/" + RandomStringUtils.randomAlphanumeric (6) + "_test.csv";
        File file = new File(fname);
        assertThat(file.exists(), is(false));
        FileWriter fileWriter = new FileWriter(file,false);
        System.out.println("new file created: " + file.getAbsolutePath());
        String[] params = {"0","ccdd"};
        writeLine(fileWriter,params,SEPARATOR);
        params = new String[]{"1", "xxxx"};
        writeLine(fileWriter,params,SEPARATOR);
        fileWriter.flush();
        fileWriter.close();
        assertThat(file.exists(), is(true));
        boolean deleteStatus = file.delete();
        assertThat(file.exists(), is(false));
    }

    /**
     * checks if a file was last modified within a number of seconds  from now
     */
    public boolean isFileModifiedWithinSecond(String filename, int nseconds){
        File file = new File(filename);
        long lstmod = file.lastModified(); //in system zone
        LocalDateTime now = LocalDateTime.now();
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(now);
        long tsnow =  now.toInstant(zoneOffset).toEpochMilli();
        //System.out.println("now:"+tsnow+" lastmod:"+ lstmod + " diff:"+String.valueOf(tsnow-lstmod));
        boolean isModifiedWithinWindow = false;
        if (tsnow-lstmod < nseconds*1000){
            isModifiedWithinWindow = true;
            //System.out.println("yes within window of modification");
        }
        return isModifiedWithinWindow ;
    }
    /**
     writes a line from an array of string words to a FileWriter
     */
    public void writeLine(FileWriter writer, String[] params, char separator) throws IOException {
        boolean firstParam = true;
        StringBuilder stringBuilder = new StringBuilder();
        String param = "";
        for (int i = 0; i < params.length; i++)
        {
            //get param
            param = params[i];
            //log.info(param);
            //if the first param in the line, separator is not needed
            if (!firstParam)
            {
                stringBuilder.append(separator);
            }
            stringBuilder.append(param);
            firstParam = false;
        }
        //prepare file to next line
        stringBuilder.append("\n");

        //add to file the line
        //log.info(stringBuilder.toString());
        writer.append(stringBuilder.toString());
    }


}
