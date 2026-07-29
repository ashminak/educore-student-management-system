package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request to create a new attendance record")
public class AttendanceCreateRequest {

    @Schema(
            description = "Enrollment ID of the student",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Enrollment Id is required")
    private Long enrollmentId;

    @Schema(
            description = "Date of attendance",
            example = "2026-07-14",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Attendance date is required")
    private LocalDate attendanceDate;

    @Schema(
            description = "Attendance status",
            example = "PRESENT",
            allowableValues = {"PRESENT", "ABSENT", "LATE", "LEAVE"},
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Status is required")
    private AttendanceStatus status;

    @Schema(
            description = "Remarks for the attendance record",
            example = "Student attended all lectures.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(max = 300)
    private String remarks;
}