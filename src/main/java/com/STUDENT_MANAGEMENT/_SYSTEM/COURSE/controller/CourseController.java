package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.service.CourseService;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/courses")
@Tag(
        name = "Course Management",
        description = "APIs for managing courses."
)
public class CourseController {
    private final CourseService courseService;

    @Operation(
            summary = "Create Course",
            description = "Create a new course.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Course created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Course details",
                    required = true
            )
            @Valid
            @RequestBody
            CourseCreateRequest request) {
        CourseResponse response =courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<CourseResponse>builder()
                                .success(true)
                                .message("Course is created")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Update Course",
            description = "Update an existing course.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CourseResponse>> update(
            @Parameter(
                    description = "Course ID",
                    example = "1"
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated course details",
                    required = true
            )
            @Valid
            @RequestBody
            CourseUpdateRequest request){
        CourseResponse response =courseService.updateCourse(id, request);
        return ResponseEntity.ok(
                        ApiResponse.<CourseResponse>builder()
                                .success(true)
                                .message("Course is updated")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Delete Course",
            description = "Delete a course by its ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @Parameter(
                    description = "Course ID",
                    example = "1"
            )
            @PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Course is deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Course",
            description = "Retrieve a course by it Department ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<CourseResponse>> getDepartment(
            @Parameter(
                    description = "Department ID",
                    example = "1"
            )
            @PathVariable Long id){
        CourseResponse response =courseService.getCourse(id);
        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course found")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Courses",
            description = "Retrieve all available courses.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Course list retrieved successfully")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<CourseSummaryResponse>>> getAllCourses(){
     List<CourseSummaryResponse> courses=courseService.getAllCourses();
     return ResponseEntity.ok(
             ApiResponse.<List<CourseSummaryResponse>>builder()
                     .success(true)
                     .message("Course List")
                     .data(courses)
                     .timestamp(LocalDateTime.now())
                     .build()
     );
    }

    @Operation(
            summary = "Get Courses (Paginated)",
            description = "Retrieve courses with pagination.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Paginated course list retrieved successfully")
    })
    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<CourseSummaryResponse>>> getAllCourses(

            @Parameter(hidden = true)
            Pageable pageable){
        Page<CourseSummaryResponse> courses=courseService.getCourses(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<CourseSummaryResponse>>builder()
                        .success(true)
                        .message("Course List")
                        .data(courses)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Search Courses",
            description = "Search courses by course name or course code.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<CourseSummaryResponse>>> searchCourses(@RequestParam @Parameter(
                                                                                              description = "Course name or course code",
                                                                                              example = "Java"
                                                                                      )String keyword,

                                                                                  @Parameter(hidden = true)
                                                                                  Pageable pageable){
        Page<CourseSummaryResponse> responses = courseService.searchCourse(keyword, pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<CourseSummaryResponse>>builder()
                        .success(true)
                        .message("Search Result")
                        .data(responses)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @Operation(
            summary = "Course Dropdown",
            description = "Retrieve courses for dropdown selection.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Dropdown retrieved successfully")
    })
    @GetMapping("/dropdown")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<CourseDropdownResponse>>>  getDropdowns(){
        List<CourseDropdownResponse> responses = courseService.getDropdown();
        return ResponseEntity.ok(
                ApiResponse.<List<CourseDropdownResponse>>builder()
                        .success(true)
                        .message("Course Dropdown fetched")
                        .data(responses)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }



}
