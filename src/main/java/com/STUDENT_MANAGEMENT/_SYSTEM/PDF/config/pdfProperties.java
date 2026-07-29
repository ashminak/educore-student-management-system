package com.STUDENT_MANAGEMENT._SYSTEM.PDF.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "pdf")
public class pdfProperties {
    private String applicationName;
    private String author;
    private String company;
    private String pageSize;
    private String defaultFont;
    private String defaultFontSize;

}
