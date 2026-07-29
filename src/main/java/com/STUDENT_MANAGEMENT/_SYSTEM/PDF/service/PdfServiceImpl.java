package com.STUDENT_MANAGEMENT._SYSTEM.PDF.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.repository.AttendanceRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.repository.MarksRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.PDF.exception.PdfGenerationException;
import com.STUDENT_MANAGEMENT._SYSTEM.PDF.util.PdfGenerator;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.repository.StudentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.repository.TeacherRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
@RequiredArgsConstructor

public class PdfServiceImpl implements PdfService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final MarksRepository marksRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public byte[] generateStudentReport(Long studentId) {
        StudentEntity student =studentRepository.findById(studentId)
                .orElseThrow(()->
                        new RuntimeException("Student not found"));
        try (
            ByteArrayOutputStream output = new ByteArrayOutputStream()
        ){
            return PdfGenerator.studentReport(student);

        } catch (DocumentException | IOException e) {
            throw new PdfGenerationException("Unable to generate PDF",e);
        }

    }

    @Override
    public byte[] generateTeacherReport(Long teacherId) {
        TeacherEntity teacher =teacherRepository.findById(teacherId)
                .orElseThrow(()->
                        new RuntimeException("Student not found"));
        try (
                ByteArrayOutputStream output =
                        new ByteArrayOutputStream()
        ){
        return PdfGenerator.teacherReport(teacher);

        } catch (DocumentException | IOException e) {
            throw new PdfGenerationException("Unable to generate PDF",e);
        }

    }


    @Override
    public byte[] generateAttendanceReport(Long attendanceId) {
        return new byte[0];
    }

    @Override
    public byte[] generateMarksReport(Long marksId) {
        return new byte[0];
    }
}
