package org.example.cloudproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.cloudproject.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Service s3Service;



    @PostMapping("/upload")
    public ResponseEntity<String>fileUpload(@RequestParam("file") MultipartFile file) {

        try {

            Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            String fileName = file.getOriginalFilename();
            s3Service.uploadFileToS3(tempFile.toFile(), fileName);

            Files.delete(tempFile);

            return new ResponseEntity<>("Fayl yuklandi: " + fileName, HttpStatus.OK);
        } catch ( IOException e) {
            return new ResponseEntity<>("Fayl yuklashda xatolik: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
