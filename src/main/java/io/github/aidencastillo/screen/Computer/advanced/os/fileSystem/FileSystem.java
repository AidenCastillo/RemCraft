package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem implements Serializable {
    private Directory root;

    public FileSystem() {
        this.root = new Directory("root", null);
    }

    public void serialize(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(this);
            outputStream.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static FileSystem deserialize(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FileSystem) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public static FileSystemEntry deserialize(java.io.File file) throws IOException {
//        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(jsonString, FileSystemEntry.class);
//    }

    public static void addEntry(String name, String type, String content, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/filesystem.json")));
        if (type.equals("file")) {

        } else if (type.equals("directory")) {

        }

    }

    public Directory getRoot() {
        return root;
    }
}

