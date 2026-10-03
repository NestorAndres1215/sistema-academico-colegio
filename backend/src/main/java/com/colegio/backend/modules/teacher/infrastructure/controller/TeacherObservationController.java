package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.teacher.application.dto.teacher_observation.CreateTeacherObservationRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_observation.TeacherObservationResponse;
import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherObservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-observation")
@Tag(name = "Teacher Observation")
public class TeacherObservationController {

    private final TeacherObservationUseCase teacherObservationUseCase;

    @Operation(summary = "Buscar observaciones de docentes")
    @GetMapping
    public ResponseEntity<Page<TeacherObservationResponse>> findByFilters(
            @RequestParam(required = false) String teacherCode,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                teacherObservationUseCase.findByFilters(
                        teacherCode,
                        startDate,
                        endDate,
                        PageRequest.of(page, size)
                )
        );
    }


    @GetMapping("/teacher/{id}")
    @Operation(summary = "Obtener observación por ID del docente")
    public ResponseEntity<TeacherObservationResponse> findByTeacherId(@PathVariable Long id) {
        return ResponseEntity.ok(teacherObservationUseCase.findByTeacher_Id(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener observación por ID")
    public ResponseEntity<TeacherObservationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherObservationUseCase.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar una observación de docente")
    public ResponseEntity<TeacherObservation> save(@Valid @RequestBody CreateTeacherObservationRequest request) {
        return ResponseEntity.ok(teacherObservationUseCase.save(request));
    }

}
