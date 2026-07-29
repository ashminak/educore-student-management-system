package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for sending an attendance warning email")
public class AttendanceWarningEmailRequest {

    @Schema(
            description = "Student's registered email address",
            example = "student@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @Schema(
            description = "Student's full name",
            example = "Rahul Sharma",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Student name is required")
    private String studentName;

    @Schema(
            description = "Student's attendance percentage",
            example = "68.50",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Attendance percentage cannot be negative"
    )
    @DecimalMax(
            value = "100.0",
            inclusive = true,
            message = "Attendance percentage cannot exceed 100"
    )
    private Double attendancePercentage;
}