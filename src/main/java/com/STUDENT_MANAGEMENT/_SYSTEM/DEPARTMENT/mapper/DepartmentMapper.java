package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.*;

import java.util.List;

@Mapper(config =  MapperConfiguration.class)
public interface DepartmentMapper {
    /*
    CREATE
     */
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "students",ignore = true)
    @Mapping(target = "teachers",ignore = true)
    @Mapping(target = "active",ignore = true)
    DepartmentEntity toEntity(DepartmentCreateRequest request);

    /*
    UPDATE
     */
    @BeanMapping(nullValuePropertyMappingStrategy =
    NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "departmentCode",ignore = true)
    @Mapping(target = "students",ignore = true)
    @Mapping(target = "teachers",ignore = true)
    @Mapping(target = "active",ignore = true)
    void updateDepartment(DepartmentUpdateRequest request,
                          @MappingTarget DepartmentEntity entity);
    /*
    ENTITY-RESPONSE
     */
    DepartmentResponse toResponse(DepartmentEntity department);
    /*
    SUMMARY RESPONSE
     */
    DepartmentSummaryResponse toSummary(DepartmentEntity department);
    /*
    DROPDOWN RESPONSE
     */
    @Mapping(target = "id",source = "id")
    @Mapping(target = "departmentName",source = "departmentName")
    DepartmentDropdownResponse toDropdown(DepartmentEntity department);
    /*
    LIST MAPPING
     */

    List<DepartmentResponse> toResponseList(List<DepartmentEntity> departments);
    List<DepartmentSummaryResponse> toSummaryList(List<DepartmentEntity> departments);
    List<DepartmentDropdownResponse> toDropDownList(List<DepartmentEntity> departments);
}
