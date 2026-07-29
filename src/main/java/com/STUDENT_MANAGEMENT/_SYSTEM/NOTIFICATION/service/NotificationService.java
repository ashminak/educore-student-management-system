package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.service;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    /*INTERNAL NOTIFICATION DASHBOARD*/
    NotificationResponse createNotification(
            User user,
            String title,
            String message,
            NotificationType type,
            NotificationPriority priority
    );
    /*
       DASHBOARD
     */
    List<NotificationSummaryResponse> getMyNotifications(Long userId);
    Page<NotificationSummaryResponse> getMyNotifications(Long userId,Pageable pageable);
    NotificationResponse getNotification(Long notificationId);

    /*
    READ
     */
    void markAsRead(Long notificationId);
    void markAllAsRead(Long userId);

    Long getUnreadCount(Long userId);

    /*
    DELETE
     */
    void deleteNotification(Long notificationId);

}
