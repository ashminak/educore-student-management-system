package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching enrollments")
public class EnrollmentSearchRequest {

    @Schema(
            description = "Student ID",
            example = "1"
    )
    private Long studentId;

    @Schema(
            description = "Course ID",
            example = "10"
    )
    private Long courseId;

    @Schema(
            description = "Semester number",
            example = "5"
    )
    private Integer semester;

    @Schema(
            description = "Academic year",
            example = "2025-2026"
    )
    private String academicYear;

    @Schema(
            description = "Enrollment status",
            example = "ENROLLED"
    )
    private EnrollmentStatus status;
}