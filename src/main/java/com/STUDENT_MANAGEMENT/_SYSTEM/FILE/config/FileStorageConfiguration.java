package com.STUDENT_MANAGEMENT._SYSTEM.FILE.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class FileStorageConfiguration {
    private final FileStorageProperties properties;

    @PostConstruct
    public void createUploadFolder() {
        createFolder(properties.getUploadLocation());
        createFolder(properties.getUploadLocation()
        +"/"+
                properties.getStudentFolder());
        createFolder(properties.getUploadLocation()
        +"/"+
                properties.getTeacherFolder());
        createFolder(properties.getUploadLocation()
        +"/"+
                properties.getDocumentFolder());
        createFolder(properties.getUploadLocation()
        +"/"+
                properties.getCourseFolder());


    }
    private void createFolder(String folder) {
        if (folder == null || folder.isBlank()) {
            throw new IllegalStateException("Folder path is missing in application.properties");
        }

        try {
            Path path = Paths.get(folder);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to create folder: " + folder, e);
        }
    }
}
