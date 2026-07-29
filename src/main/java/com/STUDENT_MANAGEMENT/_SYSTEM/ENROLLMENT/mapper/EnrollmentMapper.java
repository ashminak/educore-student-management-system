package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.StudentCourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.mapstruct.*;

import java.util.List;

@Mapper(config =  MapperConfiguration.class)
public interface EnrollmentMapper {

    /*
    CREATE
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course",ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "grade",ignore = true)
    EnrollmentEntity toEntity(EnrollmentCreateRequest request);
    /*
    UPDATE
     */
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course",ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "semester",ignore = true)
    @Mapping(target = "academicYear",ignore = true)
    @Mapping(target = "grade",ignore = true)
    @Mapping(target = "enrollmentDate",ignore = true)
    EnrollmentEntity updateEntity(EnrollmentUpdateRequest request, @MappingTarget EnrollmentEntity entity);
    /*
    ENTITY-RESPONSE
     */
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName",source = "student.user.fullName")
    @Mapping(target = "courseId",source = "course.id")
    @Mapping(target = "courseName",source = "course.courseName")
    EnrollmentResponse toResponse(EnrollmentEntity entity);
    /*
    ENTITY-SUMMARY
     */
    @Mapping(target = "studentName",source = "student.user.fullName")
    @Mapping(target = "courseName",source = "course.courseName")
    EnrollmentSummaryResponse toSummaryResponse(EnrollmentEntity entity);

    /*
    STUDENT-COURSE RESPONSE
     */
    @Mapping(target = "courseId",source = "course.id")
    @Mapping(target = "courseCode",source = "course.courseCode")
    @Mapping(target = "courseName",source = "course.courseName")
    @Mapping(target = "credits",source = "course.credits")
    @Mapping(target = "semester",source = "course.semester")
    StudentCourseResponse toStudentCourseResponse(EnrollmentEntity entity);
    /*
    LIST
     */
    List<EnrollmentSummaryResponse> toSummaryList(List<EnrollmentEntity> entities);
    List<EnrollmentResponse> toResponseList(List<EnrollmentEntity> entities);
    List<StudentCourseResponse>  toStudentCourseList(List<EnrollmentEntity> entities);



}
