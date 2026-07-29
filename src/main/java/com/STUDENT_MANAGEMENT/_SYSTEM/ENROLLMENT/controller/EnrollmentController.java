package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentSearchRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.service.EnrollmentService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(
        name = "Enrollment Management",
        description = "APIs for managing student enrollments."
)
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Operation(
            summary = "Create Enrollment",
            description = "Enroll a student in a course.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Enrollment created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> createEnrollment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Enrollment details",
                    required = true
            )
            @Valid @RequestBody EnrollmentCreateRequest  request) {
        EnrollmentResponse  response= enrollmentService.createEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EnrollmentResponse>builder()
                                .success(true)
                                .message("Student has been Enrolled successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Update Enrollment",
            description = "Update an existing enrollment.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enrollment updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Enrollment not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> updateEnrollment(
            @Parameter(
                    description = "Enrollment ID",
                    example = "1"
            )@PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated enrollment details",
                    required = true
            )
            @Valid @RequestBody EnrollmentUpdateRequest  request) {
        EnrollmentResponse  response= enrollmentService.updateEnrollment(request,id);
        return ResponseEntity.ok(
                        ApiResponse.<EnrollmentResponse>builder()
                                .success(true)
                                .message("Enrollment updated successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Delete Enrollment",
            description = "Delete an enrollment by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enrollment deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Enrollment not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(
            @Parameter(
                    description = "Enrollment ID",
                    example = "1"
            )
            @PathVariable Long id) {
         enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Enrollment deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Enrollment",
            description = "Retrieve enrollment details by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enrollment retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Enrollment not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> getEnrollment(
            @Parameter(
                    description = "Enrollment ID",
                    example = "1"
            )
            @PathVariable Long id) {
        EnrollmentResponse response = enrollmentService.getEnrollment(id);
        return ResponseEntity.ok(
                ApiResponse.<EnrollmentResponse>builder()
                        .success(true)
                        .message("Enrollment found")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Enrollments",
            description = "Retrieve all enrollments.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enrollments retrieved successfully")
    })
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<EnrollmentSummaryResponse>>> getEnrollments() {
        List<EnrollmentSummaryResponse> response = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(
                ApiResponse.<List<EnrollmentSummaryResponse>>builder()
                        .success(true)
                        .message("Enrollment list")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Enrollments (Paginated)",
            description = "Retrieve enrollments with pagination.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enrollments retrieved successfully")
    })
    @GetMapping("/page")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<EnrollmentSummaryResponse>>> getEnrollments(
            @Parameter(hidden = true)
            Pageable pageable) {
        Page<EnrollmentSummaryResponse> response = enrollmentService.getAllEnrollments(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<EnrollmentSummaryResponse>>builder()
                        .success(true)
                        .message("Enrollment Page fetched")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Search Enrollments",
            description = "Search enrollments by student name, course name, or academic year.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<EnrollmentSummaryResponse>>> searchEnrollments(
            @Parameter(
                    description = "Search keyword",
                    example = "Java"
            )
            @RequestParam String keyword,
                                                                                          @Parameter(hidden = true)
                                                                                          Pageable pageable) {
        Page<EnrollmentSummaryResponse> responses = enrollmentService.searchEnrollments(pageable, keyword);
        return ResponseEntity.ok(
                ApiResponse.<Page<EnrollmentSummaryResponse>>builder()
                        .success(true)
                        .message("search completed")
                        .data(responses)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
