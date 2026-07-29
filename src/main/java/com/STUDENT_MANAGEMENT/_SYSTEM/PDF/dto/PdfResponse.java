package com.STUDENT_MANAGEMENT._SYSTEM.PDF.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response containing generated PDF information")
public class PdfResponse {

    @Schema(
            description = "Generated PDF file name",
            example = "student-report.pdf"
    )
    private String fileName;

    @Schema(
            description = "Content type of the PDF file",
            example = "application/pdf"
    )
    private String contentType;

    @Schema(
            description = "PDF file content in byte array (Base64 encoded in JSON responses)"
    )
    private byte[] data;

    @Schema(
            description = "Size of the generated PDF in bytes",
            example = "248576"
    )
    private Long fileSize;

    @Schema(
            description = "Date and time when the PDF was generated",
            example = "2026-07-14T18:45:30"
    )
    private LocalDateTime generatedAt;
}