package io.github.aidencastillo.screen.Computer.advanced.os;

import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {
    String path = "src/test/resources/filesystem.json";

    public FileSystemTest() throws IOException {

    }

    @Test
    public void testFileSystem() throws IOException {
        FileSystem fileSystem = new FileSystem();

        // Adding a directory and files
        Directory directory = new Directory("documents", null);
        Directory directory2 = new Directory("documents2", null);
        File file1 = new File("document1.txt", "Content of document 1");
        File file2 = new File("document2.txt", "Content of document 2");
        File file3 = new File("document3.txt", "Content of document 3");
        File file4 = new File("document4.txt", "Content of document 4");

        directory.addChild(file1);
        directory.addChild(file2);

        directory2.addChild(file3);
        directory2.addChild(file4);

        fileSystem.getRoot().addChild(directory);
        fileSystem.getRoot().addChild(directory2);

        System.out.println(fileSystem.getRoot().getChildren().get(0).getName());
        System.out.println(fileSystem.getRoot().getChildren().get(1).getName());
        System.out.println(fileSystem.getRoot().getChildren().get(0).getChildren().get(0).getName());

//        assertEquals("documents", fileSystem.getRoot().getChildren().get(0).getName());
//        assertEquals("documents2", fileSystem.getRoot().getChildren().get(1).getName());

        java.io.File outputFile = new java.io.File("src/main/resources/output.dat");
//            fileSystem.getRoot().serialize(outputFile);

        fileSystem.serialize("src/main/resources/output.dat");
        FileSystem deserializedFileSystem = fileSystem.deserialize("src/main/resources/output.dat");

        deserializedFileSystem.getRoot().getChildren().get(0).getName();

    }

    @Test
    public void testGetFile() {

    }

}
