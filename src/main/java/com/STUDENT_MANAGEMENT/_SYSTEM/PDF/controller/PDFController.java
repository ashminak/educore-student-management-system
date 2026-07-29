package com.STUDENT_MANAGEMENT._SYSTEM.PDF.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.PDF.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pdf")
@Tag(
        name = "PDF Reports",
        description = "APIs for generating and downloading PDF reports for students, teachers, attendance, and marks."
)
public class PDFController {

    private final PdfService pdfService;

    @Operation(
            summary = "Generate Student Report",
            description = "Generate and download a PDF report containing complete student information.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student report generated successfully",
                    content = @Content(
                            mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
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
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<byte[]> studentReport(
            @Parameter(
                    description = "Student ID",
                    example = "1"
            )
            @PathVariable Long studentId) {
        byte[] pdf = pdfService.generateStudentReport(studentId);
        return buildPdfResponse(
                pdf,
               "student-report.pdf"
        );
    }
    @Operation(
            summary = "Generate Teacher Report",
            description = "Generate and download a PDF report containing complete teacher information.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Teacher report generated successfully",
                    content = @Content(
                            mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Teacher not found"
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
    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<byte[]> teacherReport(
            @Parameter(
                    description = "Teacher ID",
                    example = "1"
            )
            @PathVariable Long teacherId) {
        byte[] pdf = pdfService.generateTeacherReport(teacherId);
        return buildPdfResponse(
                pdf,
                "teacher-report.pdf"
        );
    }
    @Operation(
            summary = "Generate Attendance Report",
            description = "Generate and download a student's attendance report in PDF format.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Attendance report generated successfully",
                    content = @Content(
                            mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
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

    @GetMapping("/attendance/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<byte[]> attendanceReport(
            @Parameter(
                    description = "Student ID",
                    example = "1"
            )
            @PathVariable Long studentId) {
        byte[] pdf = pdfService.generateAttendanceReport(studentId);
        return buildPdfResponse(
                pdf,
                "attendance-report.pdf"
        );
    }

    @Operation(
            summary = "Generate Marks Report",
            description = "Generate and download a student's marks report in PDF format.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Marks report generated successfully",
                    content = @Content(
                            mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
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
    @GetMapping("/marks/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<byte[]> marksReport(
            @Parameter(
                    description = "Student ID",
                    example = "1"
            )
            @PathVariable Long studentId) {
        byte[] pdf = pdfService.generateMarksReport(studentId);
        return buildPdfResponse(
                pdf,
                "marks-report.pdf"
        );
    }

    ResponseEntity<byte[]> buildPdfResponse(byte[] pdf, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdf.length);
        headers.setContentDisposition(
                ContentDisposition.attachment().filename(fileName).build());
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
