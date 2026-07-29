package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching notifications")
public class NotificationSearchRequest {

    @Schema(
            description = "User ID whose notifications are to be searched",
            example = "1"
    )
    private Long userId;

    @Schema(
            description = "Filter notifications by type",
            example = "ATTENDANCE"
    )
    private NotificationType notificationType;

    @Schema(
            description = "Filter notifications by priority",
            example = "HIGH"
    )
    private NotificationPriority priority;

    @Schema(
            description = "Filter notifications by read status",
            example = "false"
    )
    private Boolean read;
}