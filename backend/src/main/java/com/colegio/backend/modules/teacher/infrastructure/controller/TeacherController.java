package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.TeacherResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherUseCase;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import com.colegio.backend.modules.user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
@Tag(name = "Teacher")
public class TeacherController {

    private final TeacherUseCase teacherUseCase;

    @Operation(summary = "Get all Teacher")
    @GetMapping
    public ResponseEntity<Page<TeacherResponse>> findByAllStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(teacherUseCase.findByAllStatus(status, search, PageRequest.of(page, size)));
    }

    @Operation(summary = "Create a new teacher")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Teacher> create(
            @RequestPart(value = "foto", required = false) MultipartFile foto,
            @RequestPart(value = "cv", required = false) MultipartFile cv,
            @Valid @RequestPart("teacher") TeacherRequest teacherRequest
    ) {
        return ResponseEntity.ok(teacherUseCase.create(teacherRequest, foto, cv));
    }


    @Operation(summary = "Activate admin")
    @PutMapping("/activate/{id}")
    public ResponseEntity<Teacher> activate(@PathVariable Long id) {
        return ResponseEntity.ok(teacherUseCase.activate(id));
    }

    @Operation(summary = "Deactivate teacher")
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Teacher> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(teacherUseCase.deactivate(id));
    }
}