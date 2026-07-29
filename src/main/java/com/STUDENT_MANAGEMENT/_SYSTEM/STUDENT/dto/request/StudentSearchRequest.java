package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSearchRequest {
    @Schema(
            description = "Search keyword",
            example = "Ashmina"
    )
    private String keyword;
    @Schema(
            description = "Department name",
            example = "Computer Science"
    )
    private String department;
    @Schema(
            description = "Semester",
            example = "4"
    )
    private Integer semester;
    @Schema(
            description = "Student active status",
            example = "true"
    )
    private Boolean active;
}

