package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.service;

import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.config.MailProperties;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.AttendanceWarningEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.EmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.MarkPublishedEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.WelcomeEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.response.EmailResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.exception.EmailSendingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Template;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    @Override

    public EmailResponse sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getFrom());
        mailMessage.setTo(emailRequest.getTo());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessage());
        try {
            mailSender.send(mailMessage);
        }catch (Exception e) {
            throw new EmailSendingException("Unable to send email.",e);
        }

        log.info("Email sent to{}", emailRequest.getTo());
        return EmailResponse.builder()
                .recipient(emailRequest.getTo())
                .subject(emailRequest.getSubject())
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();
    }


    @Override

    public EmailResponse sendWelcomeEmail(WelcomeEmailRequest request) {
        String message = """

                Welcome %s,

                Welcome to Student Management System.

                We are happy to have you with us.

                Regards

                Student Management System

                """
                .formatted(
                        request.getFullName());
        return sendEmail(
                EmailRequest.builder()
                        .to(request.getEmail())
                        .subject("Welcome")
                        .message(message)
                        .build()
        );
    }

    @Override

    public EmailResponse sendAttendanceWarningEmail(AttendanceWarningEmailRequest request) {
        String message = """

                Hello %s,

                Your attendance is %.2f%%.

                Please improve your attendance.

                Regards

                Student Management System

                """

                .formatted(

                        request.getStudentName(),

                        request.getAttendancePercentage()

                 );

        return sendEmail(
                EmailRequest.builder()
                        .to(request.getEmail())
                        .subject("Attendance Warning")
                        .message(message)
                        .build()
        );
    }

    @Override

    public EmailResponse sendMarkPublishedEmail(MarkPublishedEmailRequest request) {
        String message = """

                Hello %s,

                Your marks have been published.

                Course : %s

                Percentage : %.2f

                Grade : %s

                Regards

                Student Management System

                """

                .formatted(

                        request.getStudentName(),

                        request.getCourseName(),

                        request.getPercentage(),

                        request.getGrade()

                );
        return sendEmail(
                EmailRequest.builder()
                        .to(request.getEmail())
                        .subject("Marks published")
                        .message(message)
                        .build()
        );
    }
}
