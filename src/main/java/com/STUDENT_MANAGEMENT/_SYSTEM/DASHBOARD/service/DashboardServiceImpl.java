package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.repository.AttendanceRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.repository.CourseRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin.AdminDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin.DashboardCardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin.TopStudentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.common.AttendanceChartResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.student.StudentDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.teacher.TeacherDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository.DepartmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.repository.MarksRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.repository.StudentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;
    private final CourseRepository courseRepository;
    @Override
    public AdminDashboardResponse getAdminDashboard() {
      Long totalStudents=  studentRepository.count();
      Long totalTeachers =  teacherRepository.count();
      Long totalCourses = courseRepository.count();
      Long totalDepartments = departmentRepository.count();
      Long totalEnrollments = attendanceRepository.count();
      Double averageMarks = marksRepository.findAveragePercentage();
      Long passCount = marksRepository.countByResultStatus(ResultStatus.PASS);
      Long totalResults = marksRepository.count();
      Double passPercentage =
              totalResults==0
              ? 0.0
                      :(passCount*100.0)/totalResults;
      Double averageAttendance = attendanceRepository.findAverageAttendance();
      List<DashboardCardResponse> cards = buildDashboardCards(
              totalStudents,
              totalTeachers,
              totalCourses,
              totalDepartments
      );
        List< TopStudentResponse> topStudents = buildTopStudents();

        return AdminDashboardResponse.builder()
                .totalStudents(totalStudents)
                .totalTeachers(totalTeachers)
                .totalCourses(totalCourses)
                .totalDepartments(totalDepartments)
                .totalEnrollments(totalEnrollments)
                .averageAttendance(averageAttendance)
                .passPercentage(passPercentage)
                .averageMarks(averageMarks)
                .topStudents(topStudents)
                .cards(cards)
                .attendanceChart(new ArrayList<>())
                .build();

    }

    @Override
    public TeacherDashboardResponse getTeacherDashboard(Long teacherId) {
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId)
                .orElseThrow(()->
                        new RuntimeException("Teacher not found!"));
        return TeacherDashboardResponse.builder()
                .totalAssignedCourses((long)teacherEntity.getCourse().size())
                .totalStudents(0L)
                .pendingMarks(0L)
                .pendingAttendance(0L)
                .averageAttendance(0.0)
                .build();
    }

    @Override
    public StudentDashboardResponse getStudentDashboard(Long studentId) {
        StudentEntity student =studentRepository.findById(studentId)
                .orElseThrow(()->
                        new RuntimeException("Student not found!"));
        MarksEntity marks =marksRepository.findByEnrollmentStudentId(studentId)
                .stream()
                .findFirst()
                .orElse(null);
        return StudentDashboardResponse.builder()
                .studentName(student.getUser().getFullName())
                .department(student.getDepartment().getDepartmentName())
                .semester(student.getSemester())
                .attendancePercentage(
                        marks==null
                                ? 0.0
                                : marks.getPercentage())
                .grade(
                        marks==null
                        ?null
                                :marks.getGrade().name())
                .build();
        }
        List<DashboardCardResponse> buildDashboardCards(
                Long totalStudents,
                Long totalTeachers,
                Long totalCourses,
                Long totalDepartments){
        List<DashboardCardResponse> cards = new ArrayList<>();
        cards.add(
                DashboardCardResponse.builder()
                        .title("Students")
                        .count(totalStudents)
                        .icon("people")
                        .color("blue")
                        .build());
            cards.add(
                    DashboardCardResponse.builder()
                            .title("Teachers")
                            .count(totalTeachers)
                            .icon("people")
                            .color("green")
                            .build());
            cards.add(
                    DashboardCardResponse.builder()
                            .title("Departments")
                            .count(totalDepartments)
                            .icon("people")
                            .color("orange")
                            .build());
            cards.add(
                    DashboardCardResponse.builder()
                            .title("Courses")
                            .count(totalCourses)
                            .icon("people")
                            .color("purple")
                            .build());
            return  cards;
        }
        List<TopStudentResponse> buildTopStudents(){
        List<MarksEntity> marksList =marksRepository.findTop10ByOrderByPercentageDesc();
        List<TopStudentResponse> result = new ArrayList<>();
           for(MarksEntity marksEntity:marksList){
               result.add(TopStudentResponse.builder()
                       .studentId(marksEntity.getEnrollment().getStudent().getId())
                       .studentName(marksEntity.getEnrollment().getStudent().getUser().getFullName())
                       .course(marksEntity.getEnrollment().getCourse().getCourseName())
                       .department(marksEntity.getEnrollment().getStudent().getDepartment().getDepartmentName())
                       .percentage(marksEntity.getPercentage())
                       .grade(marksEntity.getGrade().name())
                       .build()
               );
           }
           return result;

        }
}
