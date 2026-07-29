package com.STUDENT_MANAGEMENT._SYSTEM.FILE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.FILE.dto.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponse uploadStudentImage(MultipartFile file);
    FileUploadResponse uploadTeacherImage(MultipartFile file);
    FileUploadResponse uploadDocument(MultipartFile file);
    Resource downloadFile(String folder,String fileName);
    void deleteFile(String fileName,String folder);
}
