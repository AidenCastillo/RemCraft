package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemEntry, Serializable {
    private String type = "directory";

    @JsonIgnore
    private String content;

    private String name;
    private List<FileSystemEntry> children;

    public Directory(String name, List<FileSystemEntry> children) {
        this.name = name;
        this.children = children != null ? new ArrayList<>(children) : new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public String getContent() {
        return null;
    }

    public void addChild(FileSystemEntry child) {
        children.add(child);
    }

    public List<FileSystemEntry> getChildren() {
        return children;
    }
}
