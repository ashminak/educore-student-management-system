package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.AttendanceWarningEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.EmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.MarkPublishedEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.WelcomeEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.response.EmailResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
@Tag(
        name = "Email Management",
        description = "APIs for sending different types of emails."
)
public class EmailController {
    private final EmailService emailService;
    @Operation(
            summary = "Send Email",
            description = "Send a custom email to the specified recipient.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Email sent successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid email request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/send")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmailResponse>> sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        EmailResponse emailResponse = emailService.sendEmail(emailRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    ApiResponse.<EmailResponse>builder()
                            .success(true)
                            .message("Email sent successfully")
                            .data(emailResponse)
                            .timestamp(LocalDateTime.now())
                            .build()
                );
    }
    @Operation(
            summary = "Send Welcome Email",
            description = "Send a welcome email to a newly registered user.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Welcome email sent successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/welcome")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmailResponse>> sendWelcomeEmail(@Valid @RequestBody WelcomeEmailRequest welcomeEmailRequest) {
        EmailResponse emailResponse = emailService.sendWelcomeEmail(welcomeEmailRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EmailResponse>builder()
                                .success(true)
                                .message("Welcome Email sent successfully")
                                .data(emailResponse)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
    @Operation(
            summary = "Send Attendance Warning Email",
            description = "Send an attendance warning email to a student whose attendance is below the required percentage.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Attendance warning email sent successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/attendance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmailResponse>> sendAttendanceWarningEmail(@Valid @RequestBody AttendanceWarningEmailRequest request) {
        EmailResponse emailResponse = emailService.sendAttendanceWarningEmail(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EmailResponse>builder()
                                .success(true)
                                .message("Attendance warning Email sent successfully")
                                .data(emailResponse)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @Operation(
            summary = "Send Marks Published Email",
            description = "Send an email to notify a student that examination marks have been published.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Marks published email sent successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/marks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmailResponse>> sendMarksEmail(@Valid @RequestBody MarkPublishedEmailRequest request) {
        EmailResponse emailResponse = emailService.sendMarkPublishedEmail(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EmailResponse>builder()
                                .success(true)
                                .message("Marks Email sent successfully")
                                .data(emailResponse)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

}
