package com.STUDENT_MANAGEMENT._SYSTEM.EMAIL.exception;

public class EmailTemplateException extends RuntimeException {
    public EmailTemplateException(String message) {
        super(message);
    }
    public EmailTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}
