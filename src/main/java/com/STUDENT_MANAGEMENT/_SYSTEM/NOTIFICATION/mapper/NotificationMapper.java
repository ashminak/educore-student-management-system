package com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.request.NotificationCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.dto.response.NotificationSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.entity.NotificationEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public interface NotificationMapper {

    /*
    CREATE
     */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "read",ignore = true)
    @Mapping(target = "readAt",ignore = true)
    @Mapping(target = "active",ignore = true)
    NotificationEntity toEntity(NotificationCreateRequest request);
    /*
    ENTITY-RESPONSE
     */
    @Mapping(target = "userId",source ="user.id")
    NotificationResponse toResponse(NotificationEntity entity);
    /*
    ENTITY-SUMMARY
     */

    NotificationSummaryResponse toSummary(NotificationEntity entity);

    /*
    LIST
     */
    List<NotificationSummaryResponse> toSummaryList(List<NotificationEntity> entities);
    List<NotificationResponse> toResponseList(List<NotificationEntity> entities);

}
