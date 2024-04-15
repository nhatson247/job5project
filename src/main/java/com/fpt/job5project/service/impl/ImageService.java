package com.fpt.job5project.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty() || image.getOriginalFilename() == null) {
            throw new IllegalArgumentException("Image file is required");
        }
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        String uploadDir = "public/images/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(storageFileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        }

        return storageFileName;
    }
}
