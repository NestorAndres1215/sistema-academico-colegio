package com.colegio.backend.modules.file.application.service;


import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileService implements FileUseCase {

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
    public String storeFile(MultipartFile file, String folder) {

        String fileName = StringUtils.cleanPath(
                file.getOriginalFilename()
        );

        if (fileName.contains("..")) {
            throw new NotFoundException("Nombre de archivo no válido");
        }

        try {
            Path folderPath = storagePath
                    .resolve(folder)
                    .normalize();

            Files.createDirectories(folderPath);

            Path destination = folderPath.resolve(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(
                        inputStream,
                        destination,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

        } catch (IOException e) {
            throw new NotFoundException(
                    "Error al guardar el archivo"
            );
        }

        return "/assets/" + folder + "/" + fileName;
    }

    @Override
    public Resource loadAsResource(String fileName)
            throws MalformedURLException {

        Path file = storagePath
                .resolve(fileName)
                .normalize();

        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        }

        throw new NotFoundException(
                "Archivo no encontrado"
        );
    }

    @Override
    public void deleteFile(String nameFile) throws IOException {

        if (nameFile.startsWith("http")) {
            nameFile = nameFile.substring(nameFile.indexOf("/assets/") + 8);
        }

        Path file = uploadFile(nameFile);

        if (Files.exists(file)) {
            FileSystemUtils.deleteRecursively(file);
        }
    }

    @Override
    public Path uploadFile(String nameFile) {
        return null;
    }
}