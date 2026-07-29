package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config =  MapperConfiguration.class)
public interface CourseMapper {
    /*
    CREATE
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "status", ignore = true)
    CourseEntity toEntity(CourseCreateRequest request);
    /*
    UPDATE
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "courseCode",ignore = true)

    void updateCourse(CourseUpdateRequest request, @MappingTarget CourseEntity course);
    /*
    ENTITY-RESPONSE
     */
    @Mapping(target = "departmentId",source = "department.id")
    @Mapping(target = "departmentName",source = "department.departmentName")
    @Mapping(target = "teacherNames",
    expression = "java(mapTeacherNames(course.getTeachers()))")
    CourseResponse  toResponse(CourseEntity course);
    /*
    ENTITY-SUMMARY
     */
    @Mapping(target = "departmentName",source = "department.departmentName")
    CourseSummaryResponse toSummary(CourseEntity course);
    /*
    ENTITY-DROPDOWN
     */

    CourseDropdownResponse toDropDown(CourseEntity course);
    /*
    LIST
     */
    List<CourseResponse> toResponseList(List<CourseEntity> course);
    List<CourseSummaryResponse> toSummaryList(List<CourseEntity> course);
    List<CourseDropdownResponse> toDropDownList(List<CourseEntity> course);
    /*
    CUSTOM METHODS
     */
    default Set<String> mapTeacherNames(Set<TeacherEntity> teachers) {
        if (teachers == null) {
            return Set.of();
        }
        return teachers.stream()
                .map(teacher ->
                        teacher.getUser().getFullName())
                .collect(Collectors.toSet());
    }

}
