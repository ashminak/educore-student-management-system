package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.service;

import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.AttendanceWarningEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.EmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.MarkPublishedEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.request.WelcomeEmailRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse sendEmail(EmailRequest emailRequest);
    EmailResponse sendWelcomeEmail(WelcomeEmailRequest welcomeEmailRequest);

    EmailResponse sendAttendanceWarningEmail(AttendanceWarningEmailRequest request);
    EmailResponse sendMarkPublishedEmail(MarkPublishedEmailRequest request);
}
