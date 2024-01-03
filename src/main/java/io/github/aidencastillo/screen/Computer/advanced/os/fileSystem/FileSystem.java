package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class FileSystem implements Serializable {
    private final Directory root;

    public FileSystem() {
        this.root = new Directory("root", null);
//        this.root.addChild(new Directory("root"));
    }

    public Directory getRoot() {
        return root;
    }

    public FileSystemEntry getEntry(String path) {
        String[] pathParts = path.split("/");
        FileSystemEntry currentEntry = root;
        for (String pathPart : pathParts) {
            if (pathPart.equals("")) {
                continue;
            }
            if (currentEntry.isDirectory()) {
                Directory currentDirectory = (Directory) currentEntry;
                boolean found = false;
                for (FileSystemEntry child : currentDirectory.getChildren()) {
                    if (child.getName().equals(pathPart)) {
                        currentEntry = child;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return currentEntry;
    }

    public File getFile(String path) {
        FileSystemEntry entry = getEntry(path);
        if (entry instanceof File) {
//            System.out.println("Successfully found file.");
            return (File) entry;
        } else {
            System.err.println("The path " + path + " does not point to a file.");
            return null;
        }
    }

    // Serialization methods
    public void serialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new java.io.File("test.json"), this);

    }
}
