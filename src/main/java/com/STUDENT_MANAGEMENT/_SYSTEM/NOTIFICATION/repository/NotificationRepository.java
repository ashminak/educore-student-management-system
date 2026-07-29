package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Long>,
        JpaSpecificationExecutor<NotificationEntity> {
    /*
        USER
     */
    List<NotificationEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    Page<NotificationEntity> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    /*
        UNREAD
     */
    List<NotificationEntity> findByUserIdAndReadFalseOrderByCreatedAtDesc(Long userId);
    Long countByUserIdAndReadFalse(Long userId);
    /*
    READ
     */
    @Modifying
    @Transactional
    @Query("""
            UPDATE NotificationEntity n 
                   SET n.read = true,
                           n.readAt = CURRENT_TIMESTAMP
                     WHERE n.id = :notificationId              
        """)
    int markAsRead(@Param("notificationId") Long notificationId);
    @Modifying
    @Transactional
    @Query("""
            UPDATE NotificationEntity n 
                   SET n.read = true,
                           n.readAt = CURRENT_TIMESTAMP
                     WHERE n.user.id = :userId       
                     AND n.read = false               
        """)
    int markAllAsRead(@Param("userId") Long userId);
    /*
    SEARCH
     */
    @Query("""
            SELECT n 
            FROM NotificationEntity n
            WHERE n.user.id =:userId
            AND (
                    LOWER(n.title)
                     LIKE LOWER(CONCAT('%',:keyword,'%'))
                     OR
                    LOWER(n.message)
                    LIKE LOWER(CONCAT('%',:keyword,'%'))                                
                    )                
                ORDER BY n.createdAt DESC                
        """)
    Page<NotificationEntity> searchNotifications(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);
    /*
    RECENT
     */
    List<NotificationEntity> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
}
