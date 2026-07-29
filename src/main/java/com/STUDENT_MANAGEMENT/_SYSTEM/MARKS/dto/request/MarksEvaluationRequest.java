package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarksEvaluationRequest {
    @NotNull
    private ResultStatus resultStatus;
    @NotNull
    private Double percentage;
    @NotNull
    private Grade grade;
}
