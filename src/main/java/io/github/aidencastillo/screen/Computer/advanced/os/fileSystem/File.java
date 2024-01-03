package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

public class File implements FileSystemEntry, Serializable {
    private String type = "file";

    private String name;
    private String content;

    private boolean directory;
    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public File( String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
