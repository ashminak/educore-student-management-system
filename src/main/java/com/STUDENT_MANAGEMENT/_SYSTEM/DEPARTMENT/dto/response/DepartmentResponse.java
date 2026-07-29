package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Detailed information about a department")
public class DepartmentResponse {

    @Schema(
            description = "Unique department ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Department name",
            example = "Computer Science and Engineering"
    )
    private String departmentName;

    @Schema(
            description = "Unique department code",
            example = "CSE"
    )
    private String departmentCode;

    @Schema(
            description = "Department description",
            example = "Offers undergraduate and postgraduate programs in Computer Science."
    )
    private String description;

    @Schema(
            description = "Head of the department",
            example = "Dr. Anil Kumar"
    )
    private String headOfDepartment;

    @Schema(
            description = "Department office location",
            example = "Block A, Second Floor"
    )
    private String officeLocation;

    @Schema(
            description = "Official department email",
            example = "cse@college.edu"
    )
    private String email;

    @Schema(
            description = "Department contact number",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Whether the department is active",
            example = "true"
    )
    private Boolean active;
}