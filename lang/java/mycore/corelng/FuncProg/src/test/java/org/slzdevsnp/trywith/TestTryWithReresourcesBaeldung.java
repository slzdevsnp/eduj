package org.slzdevsnp.trywith;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class TestTryWithReresourcesBaeldung {

    private static final String filename="/tmp/file.txt";

    @BeforeAll
    public static void init() throws IOException  {
        File file = new File(filename);
        if (file.exists()){ file.delete(); }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("hello\n");
        writer.write("world!");
        writer.close();
    }

    @Test
    void TestTryWithResource(){
        int line_cnt = 0;
        // scanner.close() is called automatically
        try (Scanner scanner = new Scanner(new File(filename))){
            while(scanner.hasNext()){
                System.out.println(scanner.nextLine());
                line_cnt++;
            }
        }catch (FileNotFoundException fnfe){fnfe.printStackTrace();}
        assertTrue(line_cnt >0);
    }

}
