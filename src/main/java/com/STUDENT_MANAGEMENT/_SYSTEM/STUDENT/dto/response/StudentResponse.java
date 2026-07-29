package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.BloodGroup;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {

    @Schema(description = "Student ID", example = "1")
    private Long studentId;

    @Schema(description = "User ID", example = "10")
    private Long userId;

    @Schema(description = "Student full name", example = "Ashmina Khatun")
    private String fullName;

    @Schema(description = "Student email", example = "ashmina@gmail.com")
    private String email;

    @Schema(description = "Username", example = "ashmina123")
    private String username;

    @Schema(description = "User role", example = "STUDENT")
    private Role role;

    @Schema(description = "Student roll number", example = "CSE2026001")
    private String rollNo;

    @Schema(description = "Department name", example = "Computer Science")
    private String department;

    @Schema(description = "Current semester", example = "4")
    private Integer semester;

    @Schema(description = "Admission date", example = "2026-07-01")
    private LocalDate admissionDate;

    @Schema(description = "Date of birth", example = "2002-05-15")
    private LocalDate dob;

    @Schema(description = "Gender", example = "FEMALE")
    private Gender gender;

    @Schema(description = "Phone number", example = "9876543210")
    private String phone;

    @Schema(description = "Address", example = "Howrah, West Bengal")
    private String address;

    @Schema(description = "Guardian name", example = "Rahim Khatun")
    private String guardianName;

    @Schema(description = "Guardian phone number", example = "9123456789")
    private String guardianPhone;

    @Schema(description = "Blood group", example = "O_POSITIVE")
    private BloodGroup bloodGroup;
}
