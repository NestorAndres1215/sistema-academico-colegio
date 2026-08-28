package com.colegio.backend.modules.file.application.dto;

public record UploadResponse(
        String fileUrl,
        String fileName,
        long fileSize
) {
}