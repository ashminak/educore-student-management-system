package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.enumm.Designation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherUpdateRequest {

    @Schema(
            description = "Department ID of the teacher",
            example = "2",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long departmentId;

    @Schema(
            description = "Teacher designation",
            example = "ASSOCIATE_PROFESSOR",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Designation designation;

    @Schema(
            description = "Highest qualification",
            example = "Ph.D. in Computer Science",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 100)
    private String qualification;

    @Schema(
            description = "Teaching experience in years",
            example = "8",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Min(0)
    @Max(60)
    private Integer experienceYears;

    @Schema(
            description = "Teacher joining date",
            example = "2023-08-15",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PastOrPresent
    private LocalDate joiningDate;

    @Schema(
            description = "Monthly salary",
            example = "85000.00",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salary;

    @Schema(
            description = "Gender of the teacher",
            example = "FEMALE",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Gender gender;

    @Schema(
            description = "10-digit mobile number",
            example = "9876543210",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String phone;

    @Schema(
            description = "Residential address",
            example = "Salt Lake, Kolkata, West Bengal",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 1000)
    private String address;
}