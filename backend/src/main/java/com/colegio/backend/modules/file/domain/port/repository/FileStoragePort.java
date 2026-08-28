package com.colegio.backend.modules.file.domain.port.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStoragePort {

    String store(MultipartFile file, String folder) throws IOException;

    Path getPath(String fileName);

    void delete(String fileName) throws IOException;
}