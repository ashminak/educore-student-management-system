package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentProfileResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public interface StudentMapper {

    /*
    CREATE
     */
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "active",constant = "true")
    StudentEntity toEntity(
          StudentCreateRequest request
    );
    /*
  UPDATE
   */
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "rollNo",ignore = true)
    @Mapping(target = "active",constant = "true")
    void updateStudent(StudentUpdateRequest request, @MappingTarget StudentEntity entity);
    /*
    RESPONSE
     */
    @Mapping(target = "studentId",source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source ="user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "department", source = "department.departmentName")
    StudentResponse toResponse(StudentEntity entity);
    /*
    PROFILE
     */
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source ="user.email")
    @Mapping(target = "department", source = "department.departmentName")
    StudentProfileResponse toProfile(StudentEntity student);

    /*
    LIST MAPPING
     */
    List<StudentResponse> toResponseList(List<StudentEntity> students);
    List<StudentSummaryResponse> toSummaryList(List<StudentEntity> students);

    /*
    SUMMARY
     */
    @Mapping(target = "id",source = "id")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "department", source = "department.departmentName")
    StudentSummaryResponse toSummary(StudentEntity student);
}
