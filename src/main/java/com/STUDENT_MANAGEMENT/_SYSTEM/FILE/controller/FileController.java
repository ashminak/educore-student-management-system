package com.STUDENT_MANAGEMENT._SYSTEM.FILE.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.FILE.dto.FileUploadResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.FILE.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(
        name = "File Management",
        description = "APIs for uploading, downloading and deleting files."
)
public class FileController {
    private final FileService fileService;

    @Operation(
            summary = "Upload Student Image",
            description = "Upload a profile image for a student.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student image uploaded successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid file"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @PostMapping(value = "/upload/student",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public ResponseEntity<ApiResponse<FileUploadResponse>> uploadStudentImage(
            @Parameter(
                    description = "Student image file",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @RequestParam("file") MultipartFile file) {
        FileUploadResponse response= fileService.uploadStudentImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<FileUploadResponse>builder()
                        .success(true)
                        .message("Student image Uploaded Successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    /*
    TEACHER
     */
    @Operation(
            summary = "Upload Teacher Image",
            description = "Upload a profile image for a teacher.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping(value = "/upload/teacher",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiResponse<FileUploadResponse>> uploadTeacherImage(
            @Parameter(
                    description = "Teacher image file",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @RequestParam("file") MultipartFile file) {
        FileUploadResponse response= fileService.uploadTeacherImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<FileUploadResponse>builder()
                        .success(true)
                        .message("Teacher image Uploaded Successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    /*
    DOCUMENT
     */
    @Operation(
            summary = "Upload Document",
            description = "Upload any supported document.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping(value = "/upload/document",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<FileUploadResponse>> uploadDocument(
            @Parameter(
                    description = "Document file",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @RequestParam("file") MultipartFile file) {
        FileUploadResponse response= fileService.uploadDocument(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<FileUploadResponse>builder()
                        .success(true)
                        .message("Document Uploaded Successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    /*
    DOWNLOAD
     */
    @Operation(
            summary = "Download File",
            description = "Download a file from the server."
    )
    @GetMapping("/{folder}/{fileName}")
    public ResponseEntity<Resource> download(
            @Parameter(
                    description = "Folder name",
                    example = "students"
            )
            @PathVariable String folder,
            @Parameter(
                    description = "File name",
                    example = "profile.jpg"
            )
            @PathVariable String fileName) {
        Resource resource = fileService.downloadFile(folder,fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; fileName=\""+
                                fileName+"\"")
                .body(resource);
    }
    /*
   DELETE
    */
    @Operation(
            summary = "Delete File",
            description = "Delete a file from the server.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @DeleteMapping("/{folder}/{fileName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(
            @Parameter(
                    description = "Folder name",
                    example = "students"
            )
            @PathVariable String folder,
            @Parameter(
                    description = "File name",
                    example = "profile.jpg"
            )
            @PathVariable String fileName) {
            fileService.deleteFile(folder, fileName);
            return ResponseEntity.ok(
                    ApiResponse.<Void>builder()
                            .success(true)
                            .message("File Deleted Successfully")
                            .timestamp(LocalDateTime.now())
                            .build()
            );

    }
}
