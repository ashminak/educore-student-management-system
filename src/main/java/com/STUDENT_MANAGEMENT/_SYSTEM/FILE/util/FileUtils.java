package com.STUDENT_MANAGEMENT._SYSTEM.FILE.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public final class FileUtils {
    private FileUtils() {

    }
    /*
    ALLOWED FILE TYPES
     */
    private static final List<String> ALLOWED_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "application/pdf"
    );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    /*
    VALIDATE FILE
     */
    public static void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File can not be null or empty");
        }
        if(file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Maximum file size is 10MB");
        }
        if(!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type");
        }
    }
    /*
    GENERATE STORED NAME
     */
    public static String generateFileName(MultipartFile file) {
        String extension = getExtension(file.getOriginalFilename());
        return UUID.randomUUID()+ "." + extension;
    }
    /*
    EXTENSION
     */
    public static String getExtension(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }
    /*
    IMAGE
     */
    public static boolean isImage(MultipartFile file) {
        return file.getContentType() !=null &&
                file.getContentType().startsWith("image");
    }
    /*
    PDF
     */
    public static boolean isPdf(MultipartFile file) {
        return "application/pdf"
                .equals(file.getContentType());
    }
}
