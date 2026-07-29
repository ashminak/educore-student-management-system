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
public class TeacherCreateRequest {

        @Schema(
                description = "User ID associated with the teacher",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "User Id is required")
        private Long userId;

        @Schema(
                description = "Unique employee code of the teacher",
                example = "EMP001",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Employee code is required")
        @Size(min = 3, max = 20)
        private String employeeCode;

        @Schema(
                description = "Department ID of the teacher",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Department is required")
        private Long departmentId;

        @Schema(
                description = "Teacher designation",
                example = "ASSISTANT_PROFESSOR",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Designation is required")
        private Designation designation;

        @Schema(
                description = "Highest qualification",
                example = "M.Tech in Computer Science",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Qualification is required")
        @Size(max = 100)
        private String qualification;

        @Schema(
                description = "Total years of teaching experience",
                example = "5",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Experience is required")
        @Min(0)
        @Max(60)
        private Integer experienceYears;

        @Schema(
                description = "Joining date of the teacher",
                example = "2024-07-01",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Joining Date is required")
        @PastOrPresent
        private LocalDate joiningDate;

        @Schema(
                description = "Monthly salary",
                example = "65000.00",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Salary is required")
        @DecimalMin(value = "0.0", inclusive = true)
        private BigDecimal salary;

        @Schema(
                description = "Gender of the teacher",
                example = "FEMALE",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Gender is required")
        private Gender gender;

        @Schema(
                description = "10-digit mobile number",
                example = "9876543210",
                requiredMode = Schema.RequiredMode.REQUIRED
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
