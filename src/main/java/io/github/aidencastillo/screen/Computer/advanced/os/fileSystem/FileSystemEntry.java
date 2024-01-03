package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface FileSystemEntry {
    String getName();
    boolean isDirectory();
    String getContent();
}
