package com.abc.fileserver.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;



    public String uploadFile(MultipartFile file) throws IOException {

        String rf = System.getProperty("user.home");
        Files.createDirectories(Paths.get(rf +uploadDir));

        // Generate a unique file name by appending the timestamp to the original file name
        String fileName = new Date().getTime() + "_" + file.getOriginalFilename();

        // Resolve the destination file path in a cross-platform manner
        Path destinationFilePath = Paths.get(uploadDir).resolve(fileName);


        // Compress the image before saving it to the specified directory
        Thumbnails.of(file.getInputStream())
                .size(800, 600)  // Resize image dimensions (800x600 in this case)
                .outputQuality(0.8)  // Compress quality (80%)
                .toFile(destinationFilePath.toFile());  // Save the compressed image to the destination

        return fileName;
    }

    public boolean deleteFile(String fileName) throws IOException {
        File file = new File(uploadDir + fileName);
        return file.exists() && file.delete();
    }

    // Retrieves file metadata
    public File[] listFiles() {
        return new File(uploadDir).listFiles();
    }

    public long getTotalFileSize() {
        File[] files = new File(uploadDir).listFiles();
        return files == null ? 0 : Arrays.stream(files).mapToLong(File::length).sum();
    }

    public long getFileCount() {
        File[] files = new File(uploadDir).listFiles();
        return files == null ? 0 : files.length;
    }
}