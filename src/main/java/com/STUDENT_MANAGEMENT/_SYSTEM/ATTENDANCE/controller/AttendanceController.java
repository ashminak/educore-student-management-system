package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendancePercentageResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.service.AttendanceService;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
@Tag(
        name = "Attendance Management",
        description = "APIs for managing student attendance"
)
public class AttendanceController {
    private final AttendanceService attendanceService;
    @Operation(
            summary = "Create Attendance",
            description = "Mark attendance for a student."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Attendance created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student or Course not found")
    })
    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<AttendanceResponse>> create(@Valid @RequestBody AttendanceCreateRequest request){
        AttendanceResponse response = attendanceService.createAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<AttendanceResponse>builder()
                                .success(true)
                                .message("Attendance marked successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Update Attendance",
            description = "Update an existing attendance record."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Attendance not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<AttendanceResponse>> update(@PathVariable Long id, @Valid @RequestBody AttendanceUpdateRequest request){
        AttendanceResponse response = attendanceService.updateAttendance(request, id);
        return ResponseEntity.ok(
                ApiResponse.<AttendanceResponse>builder()
                        .success(true)
                        .message("Attendance updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Delete Attendance",
            description = "Soft delete an attendance record."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Attendance not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
        public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Attendance deleted")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Attendance",
            description = "Fetch attendance details by attendance ID."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Attendance not found")
    })
    @GetMapping("/attendance/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<AttendanceResponse>> getAttendance(@PathVariable Long studentId){
        AttendanceResponse response = attendanceService.getAttendance(studentId);
        return ResponseEntity.ok(
                ApiResponse.<AttendanceResponse>builder()
                        .success(true)
                        .message("Attendance fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Attendance",
            description = "Retrieve all attendance records."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance list retrieved successfully")
    })
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<AttendanceSummaryResponse>>> getAllAttendance(){
        List<AttendanceSummaryResponse> response = attendanceService.getAllAttendance();
        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceSummaryResponse>>builder()
                        .success(true)
                        .message("Attendance List fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Attendance Page",
            description = "Retrieve attendance records with pagination."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance page retrieved successfully")
    })
    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<AttendanceSummaryResponse>>> getAllAttendance(Pageable pageable){
        Page<AttendanceSummaryResponse> response = attendanceService.getAllAttendance(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<AttendanceSummaryResponse>>builder()
                        .success(true)
                        .message("Attendance Page fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Search Attendance",
            description = "Search attendance by student name or course name."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance search completed")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<AttendanceSummaryResponse>>> searchAttendance(Pageable pageable,
                                                                                         @RequestParam(required = false) String keyword){
        Page<AttendanceSummaryResponse> response = attendanceService.searchAttendance(pageable, keyword);
        return ResponseEntity.ok(
                ApiResponse.<Page<AttendanceSummaryResponse>>builder()
                        .success(true)
                        .message("Attendance searched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Student Attendance",
            description = "Retrieve attendance records of a student."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student attendance retrieved successfully")
    })
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<AttendanceSummaryResponse>>> getStudentAttendance(@PathVariable Long studentId){
        List<AttendanceSummaryResponse> response = attendanceService.getStudentAttendance(studentId);
        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceSummaryResponse>>builder()
                        .success(true)
                        .message("Student Attendance fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Course Attendance",
            description = "Retrieve attendance records of a course."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course attendance retrieved successfully")
    })
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<AttendanceSummaryResponse>>> getCourseAttendance(@PathVariable Long courseId){
        List<AttendanceSummaryResponse> response = attendanceService.getCourseAttendance(courseId);
        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceSummaryResponse>>builder()
                        .success(true)
                        .message("Course Attendance fetched successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Attendance Percentage",
            description = "Calculate attendance percentage of a student."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Attendance percentage calculated successfully")
    })
    @GetMapping("/student/{studentId}/percentage")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    private ResponseEntity<ApiResponse<AttendancePercentageResponse>> getAttendancePercentage(@PathVariable Long studentId){
        AttendancePercentageResponse response = attendanceService.getAttendancePercentage(studentId);
        return ResponseEntity.ok(
                ApiResponse.<AttendancePercentageResponse>builder()
                        .success(true)
                        .message("Attendance percentage calculated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
