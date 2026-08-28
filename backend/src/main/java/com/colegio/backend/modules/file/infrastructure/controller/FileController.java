package com.colegio.backend.modules.file.infrastructure.controller;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@Tag(name = "File")
public class FileController {

    private final FileUseCase fileUseCase;

    @GetMapping("/assets/{folder}/{filename:.+}")
    public ResponseEntity<Resource> getAsset(
            @PathVariable String folder,
            @PathVariable String filename
    ) throws MalformedURLException {

        Resource resource = fileUseCase.loadAsResource(
                folder + "/" + filename
        );

        return ResponseEntity.ok()
                .contentType(MediaTypeFactory
                        .getMediaType(resource)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }
}