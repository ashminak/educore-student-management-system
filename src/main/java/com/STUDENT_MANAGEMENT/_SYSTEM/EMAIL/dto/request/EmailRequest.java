package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for sending a custom email")
public class EmailRequest {

    @Schema(
            description = "Recipient's email address",
            example = "student@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Recipient email is required")
    @Email(message = "Invalid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String to;

    @Schema(
            description = "Subject of the email",
            example = "Semester Examination Schedule",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Subject is required")
    @Size(max = 200, message = "Subject cannot exceed 200 characters")
    private String subject;

    @Schema(
            description = "Content of the email",
            example = "Dear Student, your semester examination will begin from 20th July 2026.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Message is required")
    @Size(max = 5000, message = "Message cannot exceed 5000 characters")
    private String message;
}