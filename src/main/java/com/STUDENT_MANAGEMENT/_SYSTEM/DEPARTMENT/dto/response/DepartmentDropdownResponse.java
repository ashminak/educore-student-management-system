package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Department information for dropdown selection")
public class DepartmentDropdownResponse {

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
}