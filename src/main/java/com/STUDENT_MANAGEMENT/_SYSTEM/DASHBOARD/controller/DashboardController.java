package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin.AdminDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.student.StudentDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.teacher.TeacherDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.service.DashboardService;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Dashboard Management",
        description = "APIs for retrieving dashboard information for Admin, Teacher, and Student."
)
public class DashboardController {
    private final DashboardService dashboardService;

    @Operation(
            summary = "Admin Dashboard",
            description = "Retrieve dashboard statistics for the administrator.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dashboard retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<AdminDashboardResponse>> getAdminDashboard() {
        AdminDashboardResponse adminDashboardResponse = dashboardService.getAdminDashboard();
        return ResponseEntity.ok(
                ApiResponse.<AdminDashboardResponse>builder()
                        .success(true)
                        .message("Admin dashboard fetched successfully")
                        .data(adminDashboardResponse)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    @Operation(
            summary = "Teacher Dashboard",
            description = "Retrieve dashboard information for a teacher.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dashboard retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse<TeacherDashboardResponse>> getTeacherDashboard(
            @Parameter(
                    description = "Teacher ID",
                    example = "1"
            )
            @PathVariable Long teacherId) {
        TeacherDashboardResponse teacherDashboardResponse = dashboardService.getTeacherDashboard(teacherId);
        return ResponseEntity.ok(
                ApiResponse.<TeacherDashboardResponse>builder()
                        .success(true)
                        .message("Teacher dashboard fetched successfully")
                        .data(teacherDashboardResponse)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
    @Operation(
            summary = "Student Dashboard",
            description = "Retrieve dashboard information for a student.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dashboard retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudentDashboardResponse>> getStudentDashboard(
            @Parameter(
                    description = "Student ID",
                    example = "1"
            )
            @PathVariable Long studentId) {
        StudentDashboardResponse studentDashboardResponse = dashboardService.getStudentDashboard(studentId);
        return ResponseEntity.ok(
                ApiResponse.<StudentDashboardResponse>builder()
                        .success(true)
                        .message("Student dashboard fetched successfully")
                        .data(studentDashboardResponse)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
