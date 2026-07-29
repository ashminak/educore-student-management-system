package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Teacher complete response")
public class TeacherResponse {

    @Schema(
            description = "Unique teacher ID",
            example = "1"
    )
    private Long teacherId;

    @Schema(
            description = "Associated user ID",
            example = "10"
    )
    private Long userId;

    @Schema(
            description = "Teacher full name",
            example = "Rahul Sharma"
    )
    private String fullName;

    @Schema(
            description = "Teacher email address",
            example = "rahul.sharma@gmail.com"
    )
    private String email;

    @Schema(
            description = "Teacher username",
            example = "rahul123"
    )
    private String username;

    @Schema(
            description = "User role",
            example = "TEACHER"
    )
    private Role role;

    @Schema(
            description = "Unique employee code",
            example = "EMP001"
    )
    private String employeeCode;

    @Schema(
            description = "Department name",
            example = "Computer Science"
    )
    private String department;

    @Schema(
            description = "Teacher designation",
            example = "ASSISTANT_PROFESSOR"
    )
    private String designation;

    @Schema(
            description = "Highest qualification",
            example = "M.Tech in Computer Science"
    )
    private String qualification;

    @Schema(
            description = "Area of specialization",
            example = "Spring Boot, Microservices"
    )
    private String specialization;

    @Schema(
            description = "Mobile number",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Residential address",
            example = "Salt Lake, Kolkata, West Bengal"
    )
    private String address;

    @Schema(
            description = "Whether the teacher is active",
            example = "true"
    )
    private Boolean active;
}