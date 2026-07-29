package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Dashboard information for a student")
public class StudentDashboardResponse {

    @Schema(
            description = "Full name of the student",
            example = "Ashmina Khatun"
    )
    private String studentName;

    @Schema(
            description = "Department in which the student is enrolled",
            example = "Computer Science"
    )
    private String department;

    @Schema(
            description = "Current semester of the student",
            example = "5"
    )
    private Integer semester;

    @Schema(
            description = "Overall attendance percentage",
            example = "88.50"
    )
    private Double attendancePercentage;

    @Schema(
            description = "Overall academic percentage",
            example = "91.25"
    )
    private Double percentage;

    @Schema(
            description = "Overall grade obtained by the student",
            example = "A+"
    )
    private String grade;

    @Schema(
            description = "Total number of enrolled courses",
            example = "6"
    )
    private Integer totalCourses;
}