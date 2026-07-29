package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.service.MarksService;
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
@RequestMapping("/api/marks")
@Tag(
        name = "Marks Management",
        description = "APIs for publishing, updating, retrieving, searching, and deleting student marks."
)
public class MarksController {
    private final MarksService marksService;

    @Operation(
            summary = "Publish Marks",
            description = "Publish examination marks for a student's enrollment.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Marks published successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<MarksResponse>> publishMarks(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Marks details",
                    required = true
            )
            @Valid @RequestBody MarksCreateRequest request) {
        MarksResponse marksResponse = marksService.publishMarks(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<MarksResponse>builder()
                                .success(true)
                                .message("Marks published successfully")
                                .data(marksResponse)
                                .timestamp(LocalDateTime.now())
                                .build()

                );
    }
    @Operation(
            summary = "Update Marks",
            description = "Update marks for an existing record.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Marks updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Marks not found")
    })
    @PutMapping("/{marksId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<MarksResponse>> updateMarks(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated marks details",
                    required = true
            )
            @Valid @RequestBody MarksUpdateRequest request,

                                                                  @Parameter(
                                                                          description = "Marks ID",
                                                                          example = "1"
                                                                  )@PathVariable Long marksId) {
        MarksResponse marksResponse = marksService.update(request, marksId);
        return ResponseEntity.ok(
                ApiResponse.<MarksResponse>builder()
                        .success(true)
                        .message("Marks updated successfully")
                        .data(marksResponse)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @Operation(
            summary = "Delete Marks",
            description = "Delete a marks record by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Marks deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Marks not found")
    })
    @DeleteMapping("/{marksId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Void>> deleteMarks(
            @Parameter(
                    description = "Marks ID",
                    example = "1"
            )
            Long marksId) {
        marksService.delete(marksId);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Marks deleted successfully successfully")
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @Operation(
            summary = "Get Marks",
            description = "Retrieve marks by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Marks retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Marks not found")
    })
    @GetMapping("/{marksId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse<MarksResponse>> getMarksById(
            @Parameter(
                    description = "Marks ID",
                    example = "1"
            )
            @PathVariable Long marksId) {
       MarksResponse marks = marksService.getById(marksId);
        return ResponseEntity.ok(
                ApiResponse.<MarksResponse>builder()
                        .success(true)
                        .message("Marks fetched successfully")
                        .data(marks)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @Operation(
            summary = "Get All Marks",
            description = "Retrieve all published marks.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Marks retrieved successfully")
    })
    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<List<MarksSummaryResponse>>> getAllMarks() {
        List<MarksSummaryResponse> marks = marksService.getAllMarks();
        return ResponseEntity.ok(
                ApiResponse.<List<MarksSummaryResponse>>builder()
                        .success(true)
                        .message("Marks fetched successfully")
                        .data(marks)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @Operation(
            summary = "Get Marks (Paginated)",
            description = "Retrieve marks with pagination.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<MarksSummaryResponse>>> getAllMarks(
            @Parameter(hidden = true)
            Pageable pageable) {
        Page<MarksSummaryResponse> marks = marksService.getAllMarks(pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<MarksSummaryResponse>>builder()
                        .success(true)
                        .message("Marks page fetched")
                        .data(marks)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @Operation(
            summary = "Search Marks",
            description = "Search marks by student name, course name, or enrollment details.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<Page<MarksSummaryResponse>>> searchMarks(
            @Parameter(hidden = true)
            Pageable pageable,
            @Parameter(
                    description = "Search keyword",
                    example = "Java"
            )
            @RequestParam String keyword) {
        Page<MarksSummaryResponse> marks = marksService.searchMarks(keyword,pageable);
        return ResponseEntity.ok(
                ApiResponse.<Page<MarksSummaryResponse>>builder()
                        .success(true)
                        .message("Search completed")
                        .data(marks)
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }





}


