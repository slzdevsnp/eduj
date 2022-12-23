package org.slzdevsnp.io;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FolderTraversalTest {


    @Test
    public void shouldTraverseDirectoryDisplay() {
        File currentDir = new File("/home/zimine/repos/lac");
        displayDirectoryContents(currentDir);
    }

    public  void displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldTraverseDirectoryStore() {
        String topDir = "/home/zimine/repos/lac";
        List<File> flist = new ArrayList<>();
        List<File> dlist = new ArrayList<>();
        storeDirectoryContents(topDir, flist, dlist);

        flist.stream().forEach(x -> {
            try {
                System.out.println("found: " + x.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public  void storeDirectoryContents(String dirPath, List<File> flist, List<File> dlist) {
        try {
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    dlist.add(file);
                    //System.out.println("directory:" + file.getCanonicalPath());
                    storeDirectoryContents(file.getCanonicalPath(), flist, dlist);
                } else {
                    flist.add(file);
                    //System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
