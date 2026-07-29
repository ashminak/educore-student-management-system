package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.service.DepartmentService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
@Tag(
        name = "Department Management",
        description = "APIs for managing departments."
)
public class DepartmentController {
    private final DepartmentService departmentService;

    @Operation(
            summary = "Create Department",
            description = "Create a new department.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Department created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentResponse>> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Department details",
                    required = true
            )
            @Valid
            @RequestBody
            DepartmentCreateRequest request) {
        DepartmentResponse response = departmentService.createDepartment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                ApiResponse.<DepartmentResponse>builder()
                        .success(true)
                        .message("Department created successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
    @Operation(
            summary = "Update Department",
            description = "Update an existing department.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentResponse>> update(
            @Parameter(
                    description = "Department ID",
                    example = "1"
            )@PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated department details",
                    required = true
            )
                                                                  @Valid
                                                                  @RequestBody
            DepartmentUpdateRequest request) {
        DepartmentResponse response = departmentService.updateDepartment(id,request);
        return ResponseEntity.ok(
                ApiResponse.<DepartmentResponse>builder()
                        .success(true)
                        .message("Department updated successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Delete Department",
            description = "Delete a department by its ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(
            @Parameter(
                    description = "Department ID",
                    example = "1"
            )
            @PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Department deleted successfully")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Department",
            description = "Retrieve department details by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Department not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartment(
            @Parameter(
                    description = "Department ID",
                    example = "1"
            )
            @PathVariable Long id){
        DepartmentResponse response = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(
                ApiResponse.<DepartmentResponse>builder()
                        .success(true)
                        .message("Department found")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get All Departments",
            description = "Retrieve all departments.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<DepartmentSummaryResponse>>> getAllDepartments(){
        List<DepartmentSummaryResponse> response = departmentService.getAllDepartments();
        return ResponseEntity.ok(
                ApiResponse.<List<DepartmentSummaryResponse>>builder()
                        .success(true)
                        .message("Department List")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Departments (Paginated)",
            description = "Retrieve departments with pagination.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
    })
    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<DepartmentSummaryResponse>>> getAllDepartment(
            @Parameter(hidden = true)
            Pageable pageable){
        Page<DepartmentSummaryResponse> response = departmentService.getAllDepartment(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<DepartmentSummaryResponse>>builder()
                        .success(true)
                        .message("Department Page")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Search Departments",
            description = "Search departments by department name or department code.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({

            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<DepartmentSummaryResponse>>> searchTeachers(
            @Parameter(
                    description = "Department name or department code",
                    example = "Computer Science"
            )
            @RequestParam String keyword,
                                                                                       @Parameter(hidden = true)
                                                                                    Pageable pageable){
        Page<DepartmentSummaryResponse> response = departmentService.searchDepartments(keyword, pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<DepartmentSummaryResponse>>builder()
                        .success(true)
                        .message("Search Result")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Department Dropdown",
            description = "Retrieve departments for dropdown selection.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Department dropdown retrieved successfully")
    })
    @GetMapping("/dropdown")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<DepartmentDropdownResponse>>> dropdown(){
        List<DepartmentDropdownResponse> responses = departmentService.getDepartmentDropdown();
        return ResponseEntity.ok(
                ApiResponse.<List<DepartmentDropdownResponse>>builder()
                        .success(true)
                        .message("Department dropdown fetched")
                        .data(responses)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
