package com.STUDENT_MANAGEMENT._SYSTEM.FILE.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadLocation;
    private String studentFolder;
    private String teacherFolder;
    private String documentFolder;
    private String courseFolder;
}
