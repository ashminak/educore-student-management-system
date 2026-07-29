package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for enrolling a student into a course")
public class EnrollmentCreateRequest {

    @Schema(
            description = "Unique student ID",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @Schema(
            description = "Unique course ID",
            example = "10",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Course ID is required")
    private Long courseId;

    @Schema(
            description = "Semester in which the student is enrolled",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot exceed 8")
    private Integer semester;

    @Schema(
            description = "Academic year",
            example = "2025-2026",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Academic year is required")
    private String academicYear;

    @Schema(
            description = "Date of enrollment",
            example = "2026-07-14",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;
}