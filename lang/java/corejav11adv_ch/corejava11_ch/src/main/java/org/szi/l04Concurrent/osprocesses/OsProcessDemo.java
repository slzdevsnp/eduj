package org.szi.l04Concurrent.osprocesses;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Scanner;

@Slf4j
public class OsProcessDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        runDirectoryListing();
    }

    static void runDirectoryListing() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ls", "-al");
        Process p = builder.directory(new File("/home/zimine")).start();
        log.info("process pid {}",p.pid());
        InputStream processOut = p.getInputStream();
        Scanner in = new Scanner(processOut);
        while(in.hasNextLine()){
            System.out.println(in.nextLine());
        }
        p.waitFor();
    }

}
