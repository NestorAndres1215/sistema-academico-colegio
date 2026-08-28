package com.colegio.backend.modules.file.infrastructure.persistence.adapter;

import com.colegio.backend.modules.file.domain.port.repository.FileStoragePort;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileStorageAdapter implements FileStoragePort {

    @Value("${storage.location}")
    private String storageLocation;

    private Path storagePath;

    @PostConstruct
    public void init() throws IOException {

        storagePath = Paths.get(storageLocation)
                .toAbsolutePath()
                .normalize();

        Files.createDirectories(storagePath);
    }

    @Override
    public String store(MultipartFile file, String folder) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path folderPath = storagePath
                .resolve(folder)
                .normalize();

        Files.createDirectories(folderPath);

        Path destination = folderPath
                .resolve(fileName)
                .normalize();

        try (InputStream inputStream = file.getInputStream()) {

            Files.copy(
                    inputStream,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }

        return "/assets/" + folder + "/" + fileName;
    }

    @Override
    public Path getPath(String fileName) {

        String relativePath = fileName
                .replaceFirst("^/assets/", "");

        return storagePath
                .resolve(relativePath)
                .normalize();
    }

    @Override
    public void delete(String fileName) throws IOException {

        String relativePath = fileName
                .replaceFirst("^/assets/", "");

        Path file = storagePath
                .resolve(relativePath)
                .normalize();

        Files.deleteIfExists(file);
    }
}