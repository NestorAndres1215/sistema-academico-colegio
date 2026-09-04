package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
@Tag(name = "Teacher")
public class TeacherController {

    private final TeacherUseCase teacherUseCase;

    @Operation(summary = "Create a new teacher")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Teacher> create(
            @RequestPart(value = "foto", required = false) MultipartFile foto,
            @RequestPart(value = "cv", required = false) MultipartFile cv,
            @Valid @RequestPart("teacher") TeacherRequest teacherRequest
    ) {
        return ResponseEntity.ok(
                teacherUseCase.create(teacherRequest, foto, cv)
        );
    }
}