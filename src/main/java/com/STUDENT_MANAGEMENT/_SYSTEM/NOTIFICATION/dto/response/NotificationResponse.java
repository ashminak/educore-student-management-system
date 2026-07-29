package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Detailed information about a notification")
public class NotificationResponse {

    @Schema(
            description = "Unique notification ID",
            example = "101"
    )
    private Long id;

    @Schema(
            description = "User ID who received the notification",
            example = "1"
    )
    private Long userId;

    @Schema(
            description = "Notification title",
            example = "Attendance Warning"
    )
    private String title;

    @Schema(
            description = "Notification message",
            example = "Your attendance has dropped below 75%. Please attend classes regularly."
    )
    private String message;

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
            description = "Whether the notification has been read",
            example = "false"
    )
    private Boolean read;

    @Schema(
            description = "Date and time when the notification was read",
            example = "2026-07-14T15:30:00",
            nullable = true
    )
    private LocalDateTime readAt;

    @Schema(
            description = "Date and time when the notification was created",
            example = "2026-07-14T10:15:30"
    )
    private LocalDateTime createdAt;
}