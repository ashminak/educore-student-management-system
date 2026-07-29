package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.BloodGroup;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {
    @Schema(
            description = "User ID associated with the student",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "User id is required")
    private Long userId;
    @Schema(
            description = "Unique student roll number",
            example = "CSE2026001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Roll no is required")
    @Size(min = 3 , max = 20)
    private String rollNo;
    @Schema(
            description = "Department ID",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Department is required")
    private Long departmentId;
    @Schema(
            description = "Current semester",
            example = "4",
            minimum = "1",
            maximum = "8"
    )
    @NotNull
    @Min(1)
    @Max(8)
    private Integer semester;
    @Schema(
            description = "Admission date",
            example = "2026-07-01"
    )
    @PastOrPresent
    @NotNull
    private LocalDate admissionDate;
    @Schema(
            description = "Student date of birth",
            example = "2002-05-15"
    )
    @Past
    @NotNull
    private LocalDate dob;
    @Schema(
            description = "Student gender",
            example = "FEMALE"
    )
    @NotNull
    private Gender gender;
    @Schema(
            description = "Student mobile number",
            example = "9876543210"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String phone;
    @Schema(
            description = "Residential address",
            example = "Howrah, West Bengal"
    )
    @NotNull
    @Size(max = 1000)
    private String address;
    @Schema(
            description = "Guardian name",
            example = "Rahim Khatun"
    )
    @Size(max = 100)
    private String guardianName;
    @Schema(
            description = "Guardian mobile number",
            example = "9123456789"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid guardian mobile"
    )
    private String guardianPhone;
    @Schema(
            description = "Blood group",
            example = "O_POSITIVE"
    )
    private BloodGroup bloodGroup;




}
