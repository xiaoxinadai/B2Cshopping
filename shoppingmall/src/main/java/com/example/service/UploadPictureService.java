package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadPictureService {

    ResponseEntity<Map<String, Object>> handleFileUpload(MultipartFile file);
}
