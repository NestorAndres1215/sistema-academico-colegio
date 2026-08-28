package com.colegio.backend.modules.file.application.service;

import com.colegio.backend.modules.file.application.validator.FileValidator;
import com.colegio.backend.modules.file.domain.port.repository.FileStoragePort;
import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FileService implements FileUseCase {

    private final FileValidator fileValidator;
    private final FileStoragePort fileStoragePort;

    @Override
    public String storeFile(MultipartFile file, String folder) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        fileValidator.validate(file);

        try {
            return fileStoragePort.store(file, folder);

        } catch (IOException e) {
            throw new NotFoundException("Error al guardar el archivo");
        }
    }

    @Override
    public Resource loadAsResource(String fileName) throws MalformedURLException {

        Path file = fileStoragePort.getPath(fileName);

        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        }

        throw new NotFoundException("Archivo no encontrado");
    }

    @Override
    public void deleteFile(String fileName) {

        if (fileName == null || fileName.isBlank()) {
            return;
        }

        try {
            fileStoragePort.delete(fileName);

        } catch (IOException e) {
            throw new NotFoundException("Error al eliminar el archivo");
        }
    }
}