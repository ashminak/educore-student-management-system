package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherProfileResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config =  MapperConfiguration.class)
public interface TeacherMapper {
    /*
    CREATE
     */

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "active",ignore = true)
    @Mapping(target = "department", ignore = true)
    TeacherEntity toEntity(TeacherCreateRequest request);

    /*
    UPDATE
     */

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "employeeCode", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateTeacher(
            TeacherUpdateRequest request,
            @MappingTarget TeacherEntity teacher
    );

    /*
    ENTITY->RESPONSE
     */
    @Mapping(target = "teacherId", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "department", source = "department.departmentName")
    TeacherResponse  toResponse(TeacherEntity teacher);

    /*
    SUMMARY RESPONSE
     */
    @Mapping(target = "teacherId", source = "id")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "department", source = "department.departmentName")
    TeacherSummaryResponse toSummary(TeacherEntity teacher);

    /*
    PROFILE RESPONSE
     */
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "department", source = "department.departmentName")
    TeacherProfileResponse toProfile(TeacherEntity teacher);
    /*
    LIST MAPPING
     */
    List<TeacherResponse> toResponseList(List<TeacherEntity> teachers);
    List<TeacherSummaryResponse> toSummaryList(List<TeacherEntity> teachers);

}
