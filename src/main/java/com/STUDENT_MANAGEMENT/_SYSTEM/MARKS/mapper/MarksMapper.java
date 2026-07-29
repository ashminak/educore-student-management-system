package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.mapper;

import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksEvaluationRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request.MarksUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response.MarksSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.config.MapperConfiguration;
import org.mapstruct.*;

import java.util.List;

@Mapper(config =  MapperConfiguration.class)
public interface MarksMapper {
    /*
    CREATE
     */
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "enrollment",ignore = true)
    @Mapping(target = "active",ignore = true)
    MarksEntity toEntity(MarksCreateRequest request);

    /*
    EVALUATE
     */
    /*
     * EVALUATE MARKS
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "internalMarks", ignore = true)
    @Mapping(target = "practicalMarks", ignore = true)
    @Mapping(target = "finalMarks", ignore = true)
    @Mapping(target = "totalMarks", ignore = true)
    @Mapping(target = "remarks", ignore = true)
    @Mapping(target = "active", ignore = true)
    void toEvaluation(
            MarksEvaluationRequest request,
            @MappingTarget MarksEntity entity
    );
    /*
    UPDATE
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "enrollment",ignore = true)
    @Mapping(target = "resultStatus",ignore = true)
    @Mapping(target = "grade",ignore = true)
    @Mapping(target = "percentage",ignore = true)
    @Mapping(target = "active",ignore = true)
    @Mapping(target = "totalMarks",ignore = true)
    MarksEntity toUpdate(MarksUpdateRequest request,@MappingTarget MarksEntity entity);

    /*
    ENTITY RESPONSE
     */
    @Mapping(target = "enrollmentId",source = "enrollment.id")
    @Mapping(target = "studentId",source = "enrollment.student.id")
    @Mapping(target = "courseId",source = "enrollment.course.id")
    @Mapping(target = "studentName",source = "enrollment.student.user.fullName")
    @Mapping(target = "courseName",source = "enrollment.course.courseName")
    MarksResponse toResponse(MarksEntity entity);

       /*
    ENTITY SUMMARY
     */
       @Mapping(target = "studentName",source = "enrollment.student.user.fullName")
       @Mapping(target = "courseName",source = "enrollment.course.courseName")
    MarksSummaryResponse toSummary(MarksEntity entity);

       /*
       LIST
        */
    List<MarksSummaryResponse> toSummaryList(List<MarksEntity> marksList);
    List<MarksResponse> toResponse(List<MarksEntity> marksList);
}
