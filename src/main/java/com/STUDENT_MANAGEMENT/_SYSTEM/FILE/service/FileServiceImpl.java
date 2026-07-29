package com.STUDENT_MANAGEMENT._SYSTEM.FILE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.FILE.config.FileStorageProperties;
import com.STUDENT_MANAGEMENT._SYSTEM.FILE.dto.FileUploadResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.FILE.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileStorageProperties properties;

    @Override
    public FileUploadResponse uploadStudentImage(MultipartFile file) {

        return upload(file,properties.getStudentFolder());
    }

    @Override
    public FileUploadResponse uploadTeacherImage(MultipartFile file) {
        return upload(file,properties.getTeacherFolder());
    }

    @Override
    public FileUploadResponse uploadDocument(MultipartFile file) {
        return upload(file,properties.getDocumentFolder());
    }

    @Override
    public Resource downloadFile(String folder,String fileName) {
        try{
            Path path =Paths.get(properties.getUploadLocation())
                    .resolve(folder)
                    .resolve(fileName);
            Resource resource = new UrlResource(path.toUri());
            if(!resource.exists()){
                throw new RuntimeException("File Not Found");
            }
            return resource;
        }catch (MalformedURLException e){
            throw new RuntimeException("Unable to download file");
        }
    }

    @Override
    public void deleteFile(String fileName,String folderName) {
        try {
            Path path =Paths.get(properties.getUploadLocation())
                    .resolve(folderName)
                    .resolve(fileName);
            Files.deleteIfExists(path);

        }catch (IOException e){
            throw new RuntimeException("Unable to delete file");
        }
    }
    FileUploadResponse upload(MultipartFile file, String folder) {
        FileUtils.validate(file);
        String storedName = FileUtils.generateFileName(file);
        try{
            Path uploadPath = Paths.get(properties.getUploadLocation(),folder);
            Files.copy(
                    file.getInputStream(),
                    uploadPath.resolve(storedName),
                    StandardCopyOption.REPLACE_EXISTING
            );

        }catch (IOException e){
            throw new RuntimeException("Upload Failed");
        }
        return FileUploadResponse.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedName)
                .fileDownLoadUri("/api/files/"+folder+"/"+storedName)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .build();

    }

    private void validate(MultipartFile file) {
        if(file.isEmpty()){
            throw new RuntimeException("Empty File");
        }
        String contentType = file.getContentType();
        if(contentType==null
        ||!(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/pdf"))){
            throw new RuntimeException("Unsupported file type");
        }
    }
    private String getExtension(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }

}
