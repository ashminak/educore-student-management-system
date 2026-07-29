package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for updating an existing department")
public class DepartmentUpdateRequest {

    @Schema(
            description = "Updated department name",
            example = "Computer Science and Engineering"
    )
    @Size(
            min = 3,
            max = 100,
            message = "Department name must be between 3 and 100 characters"
    )
    private String departmentName;

    @Schema(
            description = "Updated department description",
            example = "Offers undergraduate and postgraduate programs in Computer Science."
    )
    @Size(
            max = 1000,
            message = "Description cannot exceed 1000 characters"
    )
    private String description;

    @Schema(
            description = "Updated Head of Department",
            example = "Dr. Anil Kumar"
    )
    @Size(
            max = 100,
            message = "Head of Department name cannot exceed 100 characters"
    )
    private String headOfDepartment;

    @Schema(
            description = "Updated office location",
            example = "Block A, Second Floor"
    )
    @Size(
            max = 200,
            message = "Office location cannot exceed 200 characters"
    )
    private String officeLocation;

    @Schema(
            description = "Updated department email address",
            example = "cse@college.edu"
    )
    @Email(message = "Invalid email address")
    private String email;

    @Schema(
            description = "Updated department contact number",
            example = "9876543210"
    )
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String phone;
}