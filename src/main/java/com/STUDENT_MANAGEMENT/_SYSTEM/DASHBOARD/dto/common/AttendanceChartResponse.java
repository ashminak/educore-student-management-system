package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Monthly attendance statistics for dashboard charts")
public class AttendanceChartResponse {

    @Schema(
            description = "Month for which attendance statistics are calculated",
            example = "January"
    )
    private String month;

    @Schema(
            description = "Average attendance percentage for the month",
            example = "87.50"
    )
    private Double attendancePercentage;
}