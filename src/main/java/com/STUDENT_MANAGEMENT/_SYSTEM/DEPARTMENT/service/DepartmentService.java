package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.service;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentCreateRequest request);
    DepartmentResponse updateDepartment(Long id, DepartmentUpdateRequest request);
    void deleteDepartment(Long id);
    List<DepartmentSummaryResponse> getAllDepartments();
    Page<DepartmentSummaryResponse> getAllDepartment(Pageable pageable);
    DepartmentResponse getDepartmentById(Long id);
    Page<DepartmentSummaryResponse> searchDepartments(String keyword,Pageable pageable);
    List<DepartmentDropdownResponse>  getDepartmentDropdown();


}
