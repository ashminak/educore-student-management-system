package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching attendance records")
public class AttendanceSearchRequest {

    @Schema(
            description = "Student ID to filter attendance records",
            example = "1",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long studentId;

    @Schema(
            description = "Course ID to filter attendance records",
            example = "5",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Long courseId;

    @Schema(
            description = "Start date of the attendance search range",
            example = "2026-07-01",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private LocalDate fromDate;

    @Schema(
            description = "End date of the attendance search range",
            example = "2026-07-31",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private LocalDate toDate;

    @Schema(
            description = "Attendance status to filter records",
            example = "PRESENT",
            allowableValues = {
                    "PRESENT",
                    "ABSENT",
                    "LATE",
                    "LEAVE"
            },
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private AttendanceStatus status;
}