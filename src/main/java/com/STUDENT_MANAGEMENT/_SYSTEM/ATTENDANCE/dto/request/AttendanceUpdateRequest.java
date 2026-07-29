package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for updating an attendance record")
public class AttendanceUpdateRequest {

    @Schema(
            description = "Updated attendance status",
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

    @Schema(
            description = "Remarks for the attendance record",
            example = "Student arrived 10 minutes late.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(
            max = 300,
            message = "Remarks cannot exceed 300 characters"
    )
    private String remarks;
}