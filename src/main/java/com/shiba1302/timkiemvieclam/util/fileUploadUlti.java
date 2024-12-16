package com.shiba1302.timkiemvieclam.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class fileUploadUlti {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try (InputStream inputStream = multipartFile.getInputStream();) {
            Path path = uploadPath.resolve(fileName);
            System.out.println("File path : " + path);
            System.out.println("filename : " + fileName);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
