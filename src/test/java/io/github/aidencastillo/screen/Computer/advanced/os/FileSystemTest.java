package io.github.aidencastillo.screen.Computer.advanced.os;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aidencastillo.block.entity.AdvancedComputerBlockEntity;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.Directory;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.File;
import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystem;

import io.github.aidencastillo.screen.Computer.advanced.os.fileSystem.FileSystemEntry;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {
    private static final File FILE = new File("test", "test content");
    private static final File FILE2 = new File("test2", "test content2");
    FileSystem fileSystem = new FileSystem();
    FileSystem fileSystem2;
//    Directory root = new Directory("root");

    ObjectMapper objectMapper = new ObjectMapper();
    String path = "src/test/resources/filesystem.json";

    public FileSystemTest() throws IOException {
//        fileSystem = objectMapper.readValue(new java.io.File(path), FileSystem.class);
    }

    @Test
    public void testFileSystem() throws IOException {
        fileSystem.getRoot().addChild(FILE);
        fileSystem.getRoot().addChild(FILE2);

        AdvancedComputerBlockEntity.fileSystem.serialize();
        fileSystem2 = objectMapper.readValue(new java.io.File(path), FileSystem.class);
        assertEquals(fileSystem2.getFile("/root/test").getContent(), fileSystem.getFile("/root/test").getContent());
        assertEquals(fileSystem2.getFile("/root/test2").getContent(), fileSystem.getFile("/root/test2").getContent());
    }

    @Test
    public void testGetFile() {
        try {
            fileSystem = objectMapper.readValue(new java.io.File(path), FileSystem.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileSystem != null) {
            // Proceed with testing
//            fileSystem.getRoot().addChild(root);
//            root.addChild(FILE);
//            root.addChild(FILE2);

            System.out.println(fileSystem.getFile("/root/test").getContent());
            assertEquals(FILE.getContent(), fileSystem.getFile("/root/test").getContent());
            assertEquals(FILE2.getContent(), fileSystem.getFile("/root/test2").getContent());
        } else {
            // Handle the case where deserialization failed
            System.err.println("Failed to deserialize the file system.");
        }
    }

}
