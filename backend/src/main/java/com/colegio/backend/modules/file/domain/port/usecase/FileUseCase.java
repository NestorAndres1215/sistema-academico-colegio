package com.colegio.backend.modules.file.domain.port.usecase;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;


public interface FileUseCase {

    String storeFile(MultipartFile file, String folder);

    Resource loadAsResource(String fileName) throws MalformedURLException;

    void deleteFile(String fileName);
}