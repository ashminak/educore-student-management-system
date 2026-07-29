package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.service;

import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksEvaluationRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface MarksService {

    MarksResponse publishMarks(MarksCreateRequest request);
    MarksResponse update(MarksUpdateRequest request, Long marksId);
    void delete(Long marksId);
    MarksResponse getById(Long marksId);
    List<MarksSummaryResponse> getAllMarks();
    Page<MarksSummaryResponse> getAllMarks(Pageable pageable);
    Page<MarksSummaryResponse> searchMarks(String keyword, Pageable pageable);
    List<MarksSummaryResponse> getStudentMarks(Long studentId);
    List<MarksSummaryResponse> getCourseMarks(Long courseId);
    public MarksResponse evaluateMarks(Long marksId, MarksEvaluationRequest request);


}
