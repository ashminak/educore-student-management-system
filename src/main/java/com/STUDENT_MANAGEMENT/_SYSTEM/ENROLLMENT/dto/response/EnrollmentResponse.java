package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Detailed information about a student's course enrollment")
public class EnrollmentResponse {

    @Schema(
            description = "Unique enrollment ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Student ID",
            example = "101"
    )
    private Long studentId;

    @Schema(
            description = "Student full name",
            example = "Ashmina Khatun"
    )
    private String studentName;

    @Schema(
            description = "Course ID",
            example = "10"
    )
    private Long courseId;

    @Schema(
            description = "Course name",
            example = "Introduction to Java"
    )
    private String courseName;

    @Schema(
            description = "Semester in which the student is enrolled",
            example = "5"
    )
    private Integer semester;

    @Schema(
            description = "Academic year",
            example = "2025-2026"
    )
    private String academicYear;

    @Schema(
            description = "Enrollment date",
            example = "2026-07-14"
    )
    private LocalDate enrollmentDate;

    @Schema(
            description = "Current enrollment status",
            example = "ENROLLED"
    )
    private EnrollmentStatus status;

    @Schema(
            description = "Current grade obtained",
            example = "A"
    )
    private Grade grade;
}