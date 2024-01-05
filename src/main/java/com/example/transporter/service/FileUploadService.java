package com.example.transporter.service;

import com.example.transporter.model.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileDTO uploadFile(MultipartFile multipartFile);

    List<FileDTO> uploadMultiFile(MultipartFile[] multipartFiles);

    FileDTO downloadFile(String filePath);
}
