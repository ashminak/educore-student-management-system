package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Teacher summary response")
public class TeacherSummaryResponse {

    @Schema(
            description = "Unique teacher ID",
            example = "1"
    )
    private Long teacherId;

    @Schema(
            description = "Full name of the teacher",
            example = "Rahul Sharma"
    )
    private String fullName;

    @Schema(
            description = "Department name",
            example = "Computer Science"
    )
    private String department;

    @Schema(
            description = "Teacher active status",
            example = "true"
    )
    private Boolean active;
}