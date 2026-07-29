package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationPriority;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.enums.NotificationType;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "notifications",
        indexes = {
                @Index(
                        name = "idx_notification_user",
                        columnList = "user_id"
                ),
                @Index(
                        name = "idx_notification_read",
                        columnList = "is_read"
                ),
                @Index(
                        name = "idx_notification_type",
                        columnList = "type"
                ),
                @Index(
                        name = "idx_notification_priority",
                        columnList = "priority"
                )
        }
)
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationPriority priority;

    @Builder.Default
    @Column(name = "is_read", nullable = false)
    private Boolean read = false;

    private LocalDateTime readAt;

    @Builder.Default
    private Boolean active = true;
}