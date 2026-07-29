package com.STUDENT_MANAGEMENT._SYSTEM.PDF.util;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public final class PdfGenerator {
    private PdfGenerator() {
    }
    /*
    STUDENT REPORT
     */

    public static byte[] studentReport(StudentEntity student){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Student Report", titleFont);
            title.setSpacingAfter(20);
            document.add(title);
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            addRow(
                    table,
                    "StudentName",
                    student.getUser().getFullName());
            addRow(
                    table,
                    "Department",
                    student.getDepartment().getDepartmentName());
            addRow(
                    table,
                    "Semester",
                    String.valueOf(
                            student.getSemester()));

            addRow(
                    table,
                    "Email",
                    student.getUser().getEmail());
            table.setSpacingBefore(20);
            document.add(table);
            document.close();
            return out.toByteArray();
        }catch (Exception ex){
            throw new RuntimeException("Unable to generate PDF",ex);
        }
    }

    public static byte[] teacherReport(TeacherEntity teacher){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Teacher Report"));
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            addRow(
                    table,
                    "Teacher",
                    teacher.getUser().getFullName());
            addRow(
                    table,
                    "Department",
                    teacher.getDepartment().getDepartmentName());
            addRow(
                    table,
                    "Email",
                    teacher.getUser().getEmail());

            document.add(table);
            document.close();
            return out.toByteArray();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    /*
    COMMON ROW
     */
    private static void addRow(
            PdfPTable table,
            String key,
            String value){
        PdfPCell cell = new PdfPCell(new Paragraph(key));
        PdfPCell cell2 = new PdfPCell(new Paragraph(value));
        table.addCell(cell);
        table.addCell(cell2);




    }

}
