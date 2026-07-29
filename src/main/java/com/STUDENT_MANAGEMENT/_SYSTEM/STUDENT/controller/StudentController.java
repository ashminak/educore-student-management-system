package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentProfileResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
        import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
@Tag(
        name = "Student Management",
        description = "Create, update, retrieve and manage students."
)
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /*
     * CREATE STUDENT
     */
    @Operation(
            summary = "Create Student",
            description = "Creates a new student."

    )

    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )

    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Student details"
    )
                                                                      @Valid @RequestBody StudentCreateRequest request) {

        StudentResponse response = studentService.createStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<StudentResponse>builder()
                                .success(true)
                                .message("Student created successfully")
                                .data(response)
                                .build()
                );


    }

    /*
     * GET STUDENT BY ID
     */
    @Operation(

            summary = "Get Student By ID",

            description = "Returns student information by ID."

    )

    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "200",

                    description = "Student retrieved successfully"

            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "404",

                    description = "Student not found"

            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "401",

                    description = "Unauthorized"

            )

    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @Parameter(
            description = "Student ID",
            example = "1"
    )
                                                                       @PathVariable Long id) {

        StudentResponse response = studentService.getStudent(id);

        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student fetched successfully")
                        .data(response)
                        .build()
        );
    }
    @Operation(
            summary = "Get Student",
            description = "Returns Student with User Id."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> getStudentByUserId(@Parameter(
            description = "Returns the student profile using the associated user ID.",
            example = "1"
    )
                                                                                  @PathVariable Long userId) {

        StudentProfileResponse response = studentService.getStudentByUserId(userId);

        return ResponseEntity.ok(
                ApiResponse.<StudentProfileResponse>builder()
                        .success(true)
                        .message("Student fetched successfully")
                        .data(response)
                        .build()
        );
    }

    /*
     * GET ALL STUDENTS
     */
    @Operation(
            summary = "Get All Students",
            description ="Returns a paginated list of students"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<StudentSummaryResponse>>> getAllStudents(
            @Parameter(
                    description = "Pagination information"
            )
            Pageable pageable) {

        Page<StudentSummaryResponse> response =
                studentService.getStudents(pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<StudentSummaryResponse>>builder()
                        .success(true)
                        .message("Students fetched successfully")
                        .data(response)
                        .build()
        );
    }
    @Operation(
            summary = "Get All Students",
            description = "Returns a complete list of all students."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentSummaryResponse>>> getAllStudents() {

        List<StudentSummaryResponse> response =
                studentService.getAllStudents();

        return ResponseEntity.ok(
                ApiResponse.<List<StudentSummaryResponse>>builder()
                        .success(true)
                        .message("Students fetched successfully")
                        .data(response)
                        .build()
        );
    }

    /*
     * UPDATE STUDENT
     */
    @Operation(
            summary = "Update Student",
            description = "Updates an existing student's information."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @Parameter(
                    description = "Student ID",
                    example = "1"
            )
            @PathVariable
            Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Required Student Information"
            )@Valid @RequestBody StudentUpdateRequest request) {

        StudentResponse response =
                studentService.updateStudent(id, request);

        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student updated successfully")
                        .data(response)
                        .build()
        );
    }

    /*
     * DELETE STUDENT
     */
    @Operation(

            summary = "Delete Student",

            description = "Soft deletes student."

    )
    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student deleted successfully"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )

    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@Parameter(
            description = "Student ID",
            example = "1"
    )
                                                           @PathVariable
                                                           Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Student deleted successfully")
                        .build()
        );
    }

    /*
     * SEARCH BY NAME
     */
    @Operation(
            summary = "Search Students",
            description = "Search students by keyword with pagination."
    )
    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Search completed successfully"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid search request"
            )

    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<StudentSummaryResponse>>> searchStudents(
            @Parameter(
                    description = "Search keyword",
                    example = "Ashmina"
            )
            @RequestParam String keyword,
            @Parameter(
                    description = "Pagination information"
            )
            Pageable pageable) {

        Page<StudentSummaryResponse> response =
                studentService.searchStudents(keyword, pageable);

        return ResponseEntity.ok(
                ApiResponse.<Page<StudentSummaryResponse>>builder()
                        .success(true)
                        .message("Search completed successfully")
                        .data(response)
                        .build()
        );
    }





}
