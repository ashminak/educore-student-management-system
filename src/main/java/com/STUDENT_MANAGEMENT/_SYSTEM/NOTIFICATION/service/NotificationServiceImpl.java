package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.service;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.entity.NotificationEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.mapper.NotificationMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.repository.NotificationRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationService;
    private final NotificationMapper notificationMapper;
    @Override
    @Transactional
    public NotificationResponse createNotification(User user, String title, String message, NotificationType type, NotificationPriority priority) {
        NotificationEntity notification =
                NotificationEntity.builder()
                        .user(user)
                        .title(title)
                        .message(message)
                        .type(type)
                        .priority(priority)
                        .build();
        NotificationEntity savedNotification =notificationService.save(notification);
        log.info("Notification created for user{}", user.getId());
        return notificationMapper.toResponse(savedNotification);


    }

    @Override
    public List<NotificationSummaryResponse> getMyNotifications(Long userId) {
        return notificationMapper.toSummaryList(notificationService.findByUserIdOrderByCreatedAtDesc(userId));
    }

    @Override
    public Page<NotificationSummaryResponse> getMyNotifications(Long userId, Pageable pageable) {
        return notificationService.findByUserIdOrderByCreatedAtDesc(userId, pageable)
                .map(notificationMapper::toSummary);
    }

    @Override
    public NotificationResponse getNotification(Long notificationId) {
        NotificationEntity notification = notificationService.findById(notificationId)
                .orElseThrow(()->
                        new RuntimeException("Notification not found for id: " + notificationId));

        return notificationMapper.toResponse(notification);
    }

    @Override
    public void markAsRead(Long notificationId) {
        notificationService.markAsRead(notificationId);
    }

    @Override
    public void markAllAsRead(Long userId) {
        notificationService.markAllAsRead(userId);
    }

    @Override
    public Long getUnreadCount(Long userId) {
        return notificationService.countByUserIdAndReadFalse(userId);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        NotificationEntity notification = notificationService.findById(notificationId)
                .orElseThrow(()->
                        new RuntimeException("Notification not found for id: " + notificationId));
        notification.setActive(false);
        notificationService.save(notification);

    }



}
