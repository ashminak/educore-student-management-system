package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.service;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.request.DepartmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.dto.response.DepartmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.mapper.DepartmentMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    @Override
    @Transactional
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        if(departmentRepository.existsByDepartmentCode(request.getDepartmentCode())){
            throw new RuntimeException("Department code already exists");
        }
        if(departmentRepository.existsByDepartmentName(request.getDepartmentName())) {
            throw new RuntimeException("Department name already exists");
        }
        DepartmentEntity department = departmentMapper.toEntity(request);
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        log.info("Department{} created successfully",savedDepartment.getDepartmentCode());
        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentUpdateRequest request) {
       DepartmentEntity department = departmentRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Department not found"));
       departmentMapper.updateDepartment(request,department);
       DepartmentEntity updatedDepartment = departmentRepository.save(department);
       log.info("Department{} updated successfully",updatedDepartment.getDepartmentCode());
       return departmentMapper.toResponse(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setActive(false);
        departmentRepository.save(department);
        log.info("Department{} deleted successfully",department.getDepartmentCode());
    }

    @Override
    public List<DepartmentSummaryResponse> getAllDepartments() {

        return departmentMapper.toSummaryList(departmentRepository.findByActiveTrue());
    }

    @Override
    public Page<DepartmentSummaryResponse> getAllDepartment(Pageable pageable) {

        return departmentRepository.findByActiveTrue(pageable)
                        .map(departmentMapper::toSummary);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
      DepartmentEntity department = departmentRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Department not found"));
      return departmentMapper.toResponse(department);
    }


    @Override
    public Page<DepartmentSummaryResponse> searchDepartments(String keyword, Pageable pageable) {

        return departmentRepository.searchDepartment(keyword, pageable).map(departmentMapper::toSummary);
    }

    @Override
    public List<DepartmentDropdownResponse> getDepartmentDropdown() {

        return departmentMapper.toDropDownList(departmentRepository.findByActiveTrue());
    }
}
