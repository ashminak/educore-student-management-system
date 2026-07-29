package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
@Tag(
        name = "Teacher Management",
        description = "Create, update, retrieve and manage teachers."
)
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @Operation(
            summary = "Create Teacher",
            description = "Creates a new teacher."

    )

    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Teacher created successfully"
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
    public ResponseEntity<ApiResponse<TeacherResponse>> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Teacher details"
            )
            @Valid
            @RequestBody
            TeacherCreateRequest request){
        TeacherResponse response = teacherService.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<TeacherResponse>builder()
                                .success(true)
                                .message("Teacher created successfully")
                                .data(response)
                                .timestamp(LocalDateTime.now())
                                .build()

                );
    }
    @Operation(
            summary = "Update Teacher",
            description = "Updates an existing teacher's information."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Teacher updated successfully"
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
                    description = "Teacher not found"
            )
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponse>> update(
            @Parameter(
                    description = "Teacher ID",
                    example = "1"
            )
            @PathVariable Long id,
                                                               @Valid @RequestBody TeacherUpdateRequest request){
        TeacherResponse response = teacherService.updateTeacher(id,request);
        return ResponseEntity.ok(
                ApiResponse.<TeacherResponse>builder()
                        .success(true)
                        .message("Teacher updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @Operation(

            summary = "Delete Teacher",

            description = "Soft deletes teacher."

    )
    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Teacher deleted successfully"
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
                    description = "Teacher not found"
            )

    })

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(
            @Parameter(
                    description = "Teacher ID",
                    example = "1"
            )
            @PathVariable Long id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Teacher deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @Operation(

            summary = "Get Teacher By ID",

            description = "Returns teacher information by ID."

    )
    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "200",

                    description = "Teacher retrieved successfully"

            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "404",

                    description = "Teacher not found"

            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(

                    responseCode = "401",

                    description = "Unauthorized"

            )

    })

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponse>> getTeacher(
            @Parameter(
                    description = "Teacher ID",
                    example = "1" )
            @PathVariable Long id){
        TeacherResponse response = teacherService.getTeacher(id);
        return ResponseEntity.ok(
                ApiResponse.<TeacherResponse>builder()
                        .success(true)
                        .message("Teacher found")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Teacher by user Id",
            description = "Returns Teacher with User Id."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeacherResponse>> getTeacherByUserId( @Parameter(
            description = "Returns the student profile using the associated user ID.",
            example = "1")
                                                                                @PathVariable Long userId){
        TeacherResponse response = teacherService.getTeacherByUserId(userId);
        return ResponseEntity.ok(
                ApiResponse.<TeacherResponse>builder()
                        .success(true)
                        .message("Teacher found by UserId")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Teachers",
            description = "Returns a complete list of all teachers."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teachers fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<TeacherSummaryResponse>>> getAllTeachers(){
        List<TeacherSummaryResponse> response = teacherService.getAllTeachers();
        return ResponseEntity.ok(
                ApiResponse.<List<TeacherSummaryResponse>>builder()
                        .success(true)
                        .message("Teacher List")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Teachers",
            description ="Returns a paginated list of teachers"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Teachers fetched successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/page")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<TeacherSummaryResponse>>> getAllTeachers(
            @Parameter(
                    description = "Pagination information"
            )
            Pageable pageable){
        Page<TeacherSummaryResponse> response = teacherService.getAllTeachers(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<TeacherSummaryResponse>>builder()
                        .success(true)
                        .message("Teachers Page")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Search Teachers",
            description = "Search teachers by keyword with pagination."
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<TeacherSummaryResponse>>> searchTeachers(
            @Parameter(
                    description = "Search keyword",
                    example = "John"
            )@RequestParam String keyword,
            @Parameter(
                    description = "Pagination information"
            )
                                                                                    Pageable pageable){
        Page<TeacherSummaryResponse> response = teacherService.searchTeachers(keyword, pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<TeacherSummaryResponse>>builder()
                        .success(true)
                        .message("Search Result")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
