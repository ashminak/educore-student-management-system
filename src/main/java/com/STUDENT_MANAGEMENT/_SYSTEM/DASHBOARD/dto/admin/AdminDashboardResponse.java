package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin;

import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.common.AttendanceChartResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Dashboard information for the administrator")
public class AdminDashboardResponse {

    @Schema(
            description = "Total number of students",
            example = "1250"
    )
    private Long totalStudents;

    @Schema(
            description = "Total number of teachers",
            example = "75"
    )
    private Long totalTeachers;

    @Schema(
            description = "Total number of courses",
            example = "45"
    )
    private Long totalCourses;

    @Schema(
            description = "Total number of departments",
            example = "8"
    )
    private Long totalDepartments;

    @Schema(
            description = "Total number of enrollments",
            example = "3150"
    )
    private Long totalEnrollments;

    @Schema(
            description = "Average attendance percentage across all students",
            example = "88.75"
    )
    private Double averageAttendance;

    @Schema(
            description = "Overall pass percentage",
            example = "91.50"
    )
    private Double passPercentage;

    @Schema(
            description = "Average marks obtained by students",
            example = "76.40"
    )
    private Double averageMarks;

    @Schema(
            description = "Top performing students"
    )
    private List<TopStudentResponse> topStudents;

    @Schema(
            description = "Dashboard summary cards"
    )
    private List<DashboardCardResponse> cards;

    @Schema(
            description = "Attendance chart data"
    )
    private List<AttendanceChartResponse> attendanceChart;
}