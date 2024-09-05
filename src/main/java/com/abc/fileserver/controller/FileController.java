package com.abc.fileserver.controller;


import com.abc.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    // Upload file endpoint
    @PostMapping("/upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileService.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("fileName", fileName);
        response.put("url", "/files/" + fileName);
        return response;
    }

    // Delete file endpoint
    @DeleteMapping("/delete/{fileName}")
    public Map<String, String> deleteFile(@PathVariable String fileName) throws IOException {
        boolean isDeleted = fileService.deleteFile(fileName);
        Map<String, String> response = new HashMap<>();
        response.put("status", isDeleted ? "deleted" : "failed");
        return response;
    }

    // List all files metadata endpoint
    @GetMapping("/list")
    public Map<String, Object> listFiles() {
        File[] files = fileService.listFiles();
        Map<String, Object> response = new HashMap<>();
        response.put("files", files);
        return response;
    }

    // Get total file count and size endpoint
    @GetMapping("/summary")
    public Map<String, Object> getFileSummary() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalFiles", fileService.getFileCount());
        response.put("totalSize", fileService.getTotalFileSize());
        return response;
    }
}