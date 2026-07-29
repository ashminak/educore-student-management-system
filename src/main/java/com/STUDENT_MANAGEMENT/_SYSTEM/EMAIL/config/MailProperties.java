package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file")
public class MailProperties {
    private String from;
    private String applicationName;
}
