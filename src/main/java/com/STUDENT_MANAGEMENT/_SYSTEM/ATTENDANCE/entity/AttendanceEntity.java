package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
    name = "attendance"
)
public class AttendanceEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "enrollment_id",
            nullable = false
    )
    private EnrollmentEntity enrollment;
    @Column(
            nullable = false,
            name = "attendance_date"
    )
    private LocalDate attendanceDate;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
    @Column(
            nullable = false,
            length = 300
    )
    private String remarks;
    @Builder.Default
    private Boolean active=true;

}
