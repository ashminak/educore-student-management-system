package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching departments")
public class DepartmentSearchRequest {

    @Schema(
            description = "Keyword used to search by department name or department code",
            example = "Computer Science"
    )
    private String keyword;

    @Schema(
            description = "Filter departments by active status",
            example = "true"
    )
    private boolean active;
}