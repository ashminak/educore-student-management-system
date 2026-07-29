package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary card displayed on the admin dashboard")
public class DashboardCardResponse {

    @Schema(
            description = "Title of the dashboard card",
            example = "Total Students"
    )
    private String title;

    @Schema(
            description = "Value displayed on the dashboard card",
            example = "1250"
    )
    private Long count;

    @Schema(
            description = "Icon representing the dashboard card (e.g., Font Awesome or Material icon class)",
            example = "fa-solid fa-user-graduate"
    )
    private String icon;

    @Schema(
            description = "Color code or predefined theme color for the card",
            example = "#4CAF50"
    )
    private String color;
}