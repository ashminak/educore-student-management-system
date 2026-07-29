package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary information about a department")
public class DepartmentSummaryResponse {

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
            description = "Indicates whether the department is active",
            example = "true"
    )
    private Boolean active;
}