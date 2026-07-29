package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.controller;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.service.NotificationService;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.user.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@PreAuthorize("isAuthenticated()")
@Tag(
        name = "Notification Management",
        description = "APIs for viewing and managing user notifications."
)
public class NotificationController {
    private final NotificationService notificationService;
    @Operation(
            summary = "Get My Notifications",
            description = "Retrieve all notifications for the currently authenticated user.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Notifications retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationSummaryResponse>>> getMyNotifications(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
                                                                                             CustomUserDetails userDetails){
        return ResponseEntity.ok(
                ApiResponse.<List<NotificationSummaryResponse>>builder()
                        .success(true)
                        .message("Notification fetched successfully")
                        .data(notificationService.getMyNotifications(userDetails.getId()))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Notifications (Paginated)",
            description = "Retrieve notifications for the authenticated user with pagination.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<NotificationSummaryResponse>>> getMyNotifications(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
                                                                                             CustomUserDetails userDetails,
            @Parameter(hidden = true)
            Pageable pageable){
        return ResponseEntity.ok(
                ApiResponse.<Page<NotificationSummaryResponse>>builder()
                        .success(true)
                        .message("Notification fetched successfully")
                        .data(notificationService.getMyNotifications(userDetails.getId(),pageable))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Notification",
            description = "Retrieve notification details by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationResponse>> getNotificationById(
            @Parameter(
                    description = "Notification ID",
                    example = "15"
            )
            @PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<NotificationResponse>builder()
                        .success(true)
                        .message("Notification fetched successfully")
                        .data(notificationService.getNotification(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Mark Notification as Read",
            description = "Mark a notification as read.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Void>> readNotification(
            @Parameter(
                    description = "Notification ID",
                    example = "15"
            )
            @PathVariable Long id){
        notificationService.markAsRead(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Notification mark as read")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Mark All Notifications as Read",
            description = "Mark all notifications of the authenticated user as read.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<Void>> readAllNotification(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails){
        notificationService.markAllAsRead(userDetails.getId());
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("All Notification mark as read")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @Operation(
            summary = "Get Unread Notification Count",
            description = "Retrieve the number of unread notifications for the authenticated user.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/unread/count")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails){
        Long unreadCount = notificationService.getUnreadCount(userDetails.getId());
        return ResponseEntity.ok(
                ApiResponse.<Long>builder()
                        .success(true)
                        .message("Unread notification count fetched successfully")
                        .data(unreadCount)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

    }
    @Operation(
            summary = "Delete Notification",
            description = "Delete a notification by ID.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Notification deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Notification not found"
            )
    })
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(
            @Parameter(
                    description = "Notification ID",
                    example = "15"
            )
            @PathVariable Long notificationId){
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Notification deleted")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

}
