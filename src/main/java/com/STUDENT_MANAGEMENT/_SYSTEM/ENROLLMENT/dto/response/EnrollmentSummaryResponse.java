package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary information about a student's enrollment")
public class EnrollmentSummaryResponse {

    @Schema(
            description = "Unique enrollment ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Student full name",
            example = "Ashmina Khatun"
    )
    private String studentName;

    @Schema(
            description = "Course name",
            example = "Introduction to Java"
    )
    private String courseName;

    @Schema(
            description = "Academic year",
            example = "2025-2026"
    )
    private String academicYear;

    @Schema(
            description = "Current enrollment status",
            example = "ENROLLED",
            allowableValues = {
                    "ENROLLED",
                    "COMPLETED",
                    "DROPPED",
                    "CANCELLED"
            }
    )
    private EnrollmentStatus status;
}