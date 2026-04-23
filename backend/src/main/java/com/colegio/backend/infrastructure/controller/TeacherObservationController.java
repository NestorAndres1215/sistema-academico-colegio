package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.TeacherObservation;
import com.colegio.backend.domain.port.usecases.TeacherObservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-observations")
@Tag(name = "Teacher Observation")
public class TeacherObservationController {

    private final TeacherObservationUseCase teacherObservationUseCase;

    @Operation(summary = "Get teacher observation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherObservation> findById(@PathVariable String id) {
        return ResponseEntity.ok(teacherObservationUseCase.findById(id));
    }

    @Operation(summary = "Search teacher observations with filters")
    @GetMapping
    public ResponseEntity<Page<TeacherObservation>> search(
            @RequestParam(required = false) String teacherId,
            @RequestParam(required = false) Status status,
            @RequestParam int page,
            @RequestParam int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(teacherObservationUseCase.findByTeacherAndStatus(teacherId, status, pageable));
    }

    @Operation(summary = "Deactivate teacher observation")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<TeacherObservation> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(teacherObservationUseCase.deactivate(id));
    }

    @Operation(summary = "Activate teacher observation")
    @PutMapping("/{id}/activate")
    public ResponseEntity<TeacherObservation> activate(@PathVariable String id) {
        return ResponseEntity.ok(teacherObservationUseCase.activate(id));
    }
}