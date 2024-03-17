package com.basic.spring.services;

import com.basic.spring.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class StorageServiceImplement implements StorageService {

    @Value("${app.upload.path:images}")
    private String path;
    private Path rootLocation;

    @Override
    public void init() {
        rootLocation = Paths.get(this.path);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not init storage, " + e.getMessage());
        }
    }

    @Override
    public String store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (file.isEmpty()) {
            return null;
        }

        try {
            // Security
            if (fileName.contains("..")) {
                throw new StorageException("Path outside current directory");
            }

            fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);

            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file, " + fileName + e.getMessage());
        }
    }
}
