package com.STUDENT_MANAGEMENT._SYSTEM.FILE.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response returned after a successful file upload")
public class FileUploadResponse {

    @Schema(
            description = "Original name of the uploaded file",
            example = "profile.jpg"
    )
    private String originalFileName;

    @Schema(
            description = "Unique file name stored on the server",
            example = "d9f84d2c-8f3a-4a8c-aaf5-1dcb95f0a8f7.jpg"
    )
    private String storedFileName;

    @Schema(
            description = "URL to download the uploaded file",
            example = "http://localhost:8080/api/files/students/d9f84d2c-8f3a-4a8c-aaf5-1dcb95f0a8f7.jpg"
    )
    private String fileDownLoadUri;

    @Schema(
            description = "MIME type of the uploaded file",
            example = "image/jpeg"
    )
    private String fileType;

    @Schema(
            description = "Size of the uploaded file in bytes",
            example = "245678"
    )
    private Long fileSize;

    @Schema(
            description = "Date and time when the file was uploaded",
            example = "2026-07-14T21:30:45"
    )
    private LocalDateTime uploadedAt;
}