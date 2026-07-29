package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Dashboard information for a teacher")
public class TeacherDashboardResponse {

    @Schema(
            description = "Total number of courses assigned to the teacher",
            example = "5"
    )
    private Long totalAssignedCourses;

    @Schema(
            description = "Total number of students taught by the teacher",
            example = "180"
    )
    private Long totalStudents;

    @Schema(
            description = "Total number of marks yet to be published",
            example = "25"
    )
    private Long pendingMarks;

    @Schema(
            description = "Total number of attendance records yet to be marked",
            example = "8"
    )
    private Long pendingAttendance;

    @Schema(
            description = "Average attendance percentage of students taught by the teacher",
            example = "87.50"
    )
    private Double averageAttendance;
}