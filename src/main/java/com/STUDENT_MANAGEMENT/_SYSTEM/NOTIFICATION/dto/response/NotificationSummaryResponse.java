package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary information about a notification")
public class NotificationSummaryResponse {

    @Schema(
            description = "Unique notification ID",
            example = "101"
    )
    private Long id;

    @Schema(
            description = "Notification title",
            example = "Attendance Warning"
    )
    private String title;

    @Schema(
            description = "Type of notification",
            example = "ATTENDANCE"
    )
    private NotificationType notificationType;

    @Schema(
            description = "Priority level of the notification",
            example = "HIGH"
    )
    private NotificationPriority priority;

    @Schema(
            description = "Indicates whether the notification has been read",
            example = "false"
    )
    private Boolean read;
}