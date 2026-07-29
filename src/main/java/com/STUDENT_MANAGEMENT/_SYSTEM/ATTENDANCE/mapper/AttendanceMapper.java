package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.entity.AttendanceEntity;

import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.*;

import java.util.List;

@Mapper(config =  MapperConfiguration.class)
public interface AttendanceMapper {
    /*
    CREATE
     */
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    AttendanceEntity toEntity(AttendanceCreateRequest request);

    /*
    UPDATE
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "attendanceDate", ignore = true)
    void toUpdate(AttendanceUpdateRequest request, @MappingTarget AttendanceEntity attendanceEntity);
    /*
    ENTITY-RESPONSE
     */
    @Mapping(target = "enrollmentId",source = "enrollment.id")
    @Mapping(target = "studentId",source = "enrollment.student.id")
    @Mapping(target = "courseId",source = "enrollment.course.id")
    @Mapping(target = "studentName", source = "enrollment.student.user.fullName")
    @Mapping(target = "courseName",source = "enrollment.course.courseName")
    AttendanceResponse toResponse(AttendanceEntity attendanceEntity);
    /*
    SUMMARY-RESPONSE
     */
    @Mapping(target = "studentName", source = "enrollment.student.user.fullName")
    @Mapping(target = "courseName",source = "enrollment.course.courseName")
    AttendanceSummaryResponse toSummary(AttendanceEntity attendanceEntity);
    /*
    LIST
     */
    List<AttendanceResponse> toResponseList(List<AttendanceEntity> attendanceEntityList);
    List<AttendanceSummaryResponse> toSummaryList(List<AttendanceEntity> attendanceEntityList);
}
