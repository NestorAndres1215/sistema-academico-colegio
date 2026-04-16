package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.teacher.TeacherRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Teacher;
import com.colegio.backend.domain.port.usecases.TeacherUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher")
@Tag(name = "Teacher")
public class TeacherController {

    private final TeacherUseCase teacherUseCase;

    @Operation(summary = "Get all teachers")
    @GetMapping
    public ResponseEntity<List<Teacher>> findAll() {
        return ResponseEntity.ok(teacherUseCase.findAll());
    }

    @Operation(summary = "Get teacher by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable String id) {
        return ResponseEntity.ok(teacherUseCase.findById(id));
    }

    @Operation(summary = "Create teacher")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Teacher> save(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @Valid @RequestPart(value = "teacher") TeacherRequest request) {

            return ResponseEntity.ok(teacherUseCase.save(file, request));

    }

    @Operation(summary = "Update teacher by ID")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Teacher> update(
            @PathVariable String id,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @Valid @RequestPart("teacher") TeacherRequest request) {

        return ResponseEntity.ok(teacherUseCase.update(id, file, request));
    }

    @Operation(summary = "Get teacher by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Teacher>> getByStatus(
            @PathVariable Status status,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search
    ) {
        System.out.println("STATUS: " + status);
        System.out.println("PAGE: " + page);
        System.out.println("SIZE: " + size);
        System.out.println("SEARCH: " + search);
        try{
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(teacherUseCase.getByStatus(status, search, pageable));} catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Operation(summary = "Deactivate teacher")
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Teacher> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(teacherUseCase.deactivate(id));
    }

    @Operation(summary = "Activate teacher")
    @PutMapping("/activate/{id}")
    public ResponseEntity<Teacher> activate(@PathVariable String id) {
        return ResponseEntity.ok(teacherUseCase.activate(id));
    }
}
