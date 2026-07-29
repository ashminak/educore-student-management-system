package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.repository.EnrollmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksEvaluationRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.mapper.MarksMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.repository.MarksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class MarksServiceImpl implements MarksService {
    private final MarksRepository marksRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final MarksMapper marksMapper;
    @Override
    public MarksResponse publishMarks(MarksCreateRequest request) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(
                request.getEnrollmentId())
                .orElseThrow(()->
                        new RuntimeException("Enrollment not found"));
        if(marksRepository.existsByEnrollmentId(enrollment.getId())) {
            throw new RuntimeException("Marks already published");
        }
        MarksEntity marks = marksMapper.toEntity(request);
        marks.setEnrollment(enrollment);
        marksRepository.save(marks);

        log.info("Marks published successfully");
        return marksMapper.toResponse(marks);
    }

    @Override
    public MarksResponse update(MarksUpdateRequest request, Long marksId) {
       MarksEntity marks = marksRepository.findById(marksId)
               .orElseThrow(()->
                        new RuntimeException("Marks not found"));
       marksMapper.toUpdate(request,marks);
       marksRepository.save(marks);
        log.info("Marks updated successfully");
        return marksMapper.toResponse(marks);
    }

    @Override
    public void delete(Long marksId) {
        MarksEntity marks = marksRepository.findById(marksId)
                .orElseThrow(()->
                        new RuntimeException("Marks not found"));
        marks.setActive(false);
        marksRepository.save(marks);
        log.info("Marks deleted successfully");
    }

    @Override
    public MarksResponse getById(Long marksId) {
        MarksEntity marks = marksRepository.findById(marksId)
                .orElseThrow(()->
                        new RuntimeException("Marks not found"));
        return marksMapper.toResponse(marks);
    }

    @Override
    public List<MarksSummaryResponse> getAllMarks() {
        return marksMapper.toSummaryList(marksRepository.findAll());
    }

    @Override
    public Page<MarksSummaryResponse> getAllMarks(Pageable pageable) {
        return marksRepository.findAll(pageable).map(marksMapper::toSummary);
    }

    @Override
    public Page<MarksSummaryResponse> searchMarks(String keyword, Pageable pageable) {
        return marksRepository.searchMarks(keyword, pageable).map(marksMapper::toSummary);
    }

    @Override
    public List<MarksSummaryResponse> getStudentMarks(Long studentId) {
        return marksMapper.toSummaryList(marksRepository.findByEnrollmentStudentId(studentId));
    }

    @Override
    public List<MarksSummaryResponse> getCourseMarks(Long courseId) {
        return marksMapper.toSummaryList(marksRepository.findByEnrollmentCourseId(courseId));
    }



    @Override
    public MarksResponse evaluateMarks(
            Long marksId,
            MarksEvaluationRequest request) {

        MarksEntity marks = marksRepository.findById(marksId)
                .orElseThrow(() ->
                        new RuntimeException("Marks not found"));

        marks.setPercentage(request.getPercentage());

        marks.setGrade(request.getGrade());

        marks.setResultStatus(request.getResultStatus());

        marksRepository.save(marks);

        return marksMapper.toResponse(marks);
    }
}
