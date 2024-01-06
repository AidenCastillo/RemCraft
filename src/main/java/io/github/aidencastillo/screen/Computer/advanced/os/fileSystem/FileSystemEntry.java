package io.github.aidencastillo.screen.Computer.advanced.os.fileSystem;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Directory.class, name = "directory"),
        @JsonSubTypes.Type(value = File.class, name = "file")
})
public interface FileSystemEntry {
    String getName();

    List<FileSystemEntry> getChildren();

    // Serialization method
    void serialize(File file) throws JsonProcessingException, IOException;

    // Implementation of serialize method

    // Deserialization method
    static FileSystemEntry deserialize(java.io.File file) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, FileSystemEntry.class);
    }

}
