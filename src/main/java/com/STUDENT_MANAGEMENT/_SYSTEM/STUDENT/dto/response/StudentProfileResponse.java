package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileResponse {
    @Schema(description = "Student full name", example = "Ashmina Khatun")
    private String fullName;

    @Schema(description = "Student email", example = "ashmina@gmail.com")
    private String email;

    @Schema(description = "Roll number", example = "CSE2026001")
    private String rollNo;

    @Schema(description = "Department", example = "Computer Science")
    private String department;

    @Schema(description = "Semester", example = "4")
    private Integer semester;

    @Schema(description = "Phone number", example = "9876543210")
    private String phone;

    @Schema(description = "Address", example = "Howrah, West Bengal")
    private String address;
}
