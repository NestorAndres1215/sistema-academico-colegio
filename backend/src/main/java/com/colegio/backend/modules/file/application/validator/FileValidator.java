package com.colegio.backend.modules.file.application.validator;

import com.colegio.backend.shared.exception.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator {

    public void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("El archivo es obligatorio");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isBlank()) {
            throw new BadRequestException("El nombre del archivo es obligatorio");
        }

        if (fileName.contains("..")) {
            throw new BadRequestException("Nombre de archivo no válido");
        }
    }
}