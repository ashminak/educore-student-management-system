package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.BloodGroup;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdateRequest {
    @Schema(
            description = "Current semester",
            example = "5"
    )
    @Min(1)
    @Max(8)
    private Integer semester;
    @PastOrPresent
    private LocalDate admissionDate;
    @Past
    private LocalDate dob;

    private Gender gender;
    @Schema(
            description = "Student phone number",
            example = "9876543210"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$"
    )
    private String phone;
    @Size(max = 1000)
    private String address;
    @Size(max = 100)
    private String guardianName;
    @Pattern(
            regexp = "^[6-9]\\d{9}$"
    )
    private String guardianPhone;
    @Schema(
            description = "Blood group",
            example = "A_POSITIVE"
    )
    private BloodGroup bloodGroup;
}

