package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for creating a new notification")
public class NotificationCreateRequest {

    @Schema(
            description = "Unique user ID who will receive the notification",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "User ID is required")
    private Long userId;

    @Schema(
            description = "Notification title",
            example = "Attendance Warning",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title is required")
    @Size(
            max = 100,
            message = "Title cannot exceed 100 characters"
    )
    private String title;

    @Schema(
            description = "Notification message",
            example = "Your attendance has fallen below 75%. Please attend upcoming classes regularly.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Message is required")
    @Size(
            max = 1000,
            message = "Message cannot exceed 1000 characters"
    )
    private String message;

    @Schema(
            description = "Type of notification",
            example = "ATTENDANCE"
    )
    @NotNull(message = "Notification type is required")
    private NotificationType type;

    @Schema(
            description = "Priority level of the notification",
            example = "HIGH"
    )
    @NotNull(message = "Priority is required")
    private NotificationPriority priority;
}