package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for sending a marks published email to a student")
public class MarkPublishedEmailRequest {

    @Schema(
            description = "Student's registered email address",
            example = "student@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @Schema(
            description = "Student's full name",
            example = "Rahul Sharma",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Student name is required")
    @Size(max = 100, message = "Student name cannot exceed 100 characters")
    private String studentName;

    @Schema(
            description = "Course name",
            example = "Java Programming",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String courseName;

    @Schema(
            description = "Overall percentage obtained by the student",
            example = "86.75",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Percentage cannot be negative"
    )
    @DecimalMax(
            value = "100.0",
            inclusive = true,
            message = "Percentage cannot exceed 100"
    )
    private Double percentage;

    @Schema(
            description = "Grade obtained by the student",
            example = "A+",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 5, message = "Grade cannot exceed 5 characters")
    private String grade;
}