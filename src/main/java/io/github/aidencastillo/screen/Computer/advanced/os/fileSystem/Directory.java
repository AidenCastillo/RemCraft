package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

@JsonTypeName("directory")
public class Directory implements Serializable, FileSystemEntry {
    private String name;
    private List<FileSystemEntry> children;

    public Directory() {
        this.name = "";
        this.children = new ArrayList<>();
    }

    public Directory(String name, List<FileSystemEntry> children) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<FileSystemEntry> getChildren() {
        return children;
    }



    // Implementation of serialize method
    @Override
    public void serialize(java.io.File file) throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(this));
        objectMapper.writeValue(file, this);
    }



    // Implementation of deserialize method
    public static Directory deserialize(java.io.File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, Directory.class);
    }

    public void addChild(FileSystemEntry entry) {
        children.add(entry);
    }
}
