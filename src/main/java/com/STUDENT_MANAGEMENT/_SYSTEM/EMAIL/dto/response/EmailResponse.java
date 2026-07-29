package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response returned after an email has been sent")
public class EmailResponse {

    @Schema(
            description = "Recipient's email address",
            example = "student@example.com"
    )
    private String recipient;

    @Schema(
            description = "Subject of the email",
            example = "Welcome to Student Management System"
    )
    private String subject;

    @Schema(
            description = "Status of the email delivery",
            example = "SENT",
            allowableValues = {
                    "SENT",
                    "FAILED",
                    "PENDING"
            }
    )
    private String status;

    @Schema(
            description = "Date and time when the email was sent",
            example = "2026-07-13T20:45:30"
    )
    private LocalDateTime sentAt;
}