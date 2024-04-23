package com.fpt.job5project.controller;

import com.fpt.job5project.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StorageController {

    IStorageService storageService;

    @GetMapping("/files/{fileName}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName)
    {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            MediaType mediaType = MediaType.IMAGE_JPEG;
            if (fileName.endsWith(".pdf")) {
                mediaType = MediaType.APPLICATION_PDF;
            }
            return ResponseEntity
                    .ok()
                    .contentType(mediaType)
                    .body(bytes);

        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/downloadWord/{fileName}")
    public ResponseEntity<byte[]> downloadWord(@PathVariable String fileName) throws IOException {
        // Load file từ resources folder
        Resource resource = new ClassPathResource("static/uploads/" + fileName);
        byte[] fileContent = Files.readAllBytes(Path.of(resource.getURI()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok().headers(headers).body(fileContent);
    }
}
