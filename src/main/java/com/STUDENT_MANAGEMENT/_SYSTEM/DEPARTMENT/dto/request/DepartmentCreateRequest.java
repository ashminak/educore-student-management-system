package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for creating a new department")
public class DepartmentCreateRequest {

    @Schema(
            description = "Unique department code",
            example = "CSE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Department code is required")
    @Size(min = 0, max = 100, message = "Department code must be between 2 and 100 characters")
    private String departmentCode;

    @Schema(
            description = "Department name",
            example = "Computer Science and Engineering",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Department name is required")
    @Size(min = 0, max = 100, message = "Department name must be between 3 and 20 characters")
    private String departmentName;

    @Schema(
            description = "Brief description of the department",
            example = "Offers undergraduate and postgraduate programs in Computer Science."
    )
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Schema(
            description = "Head of the department",
            example = "Dr. Anil Kumar"
    )
    @Size(max = 100, message = "Head of Department name cannot exceed 100 characters")
    private String headOfDepartment;

    @Schema(
            description = "Office location of the department",
            example = "Block A, Second Floor"
    )
    @Size(max = 200, message = "Office location cannot exceed 200 characters")
    private String officeLocation;

    @Schema(
            description = "Official department email address",
            example = "cse@college.edu"
    )
    @Email(message = "Invalid email")
    private String email;

    @Schema(
            description = "Department contact number",
            example = "9876543210"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String phone;
}