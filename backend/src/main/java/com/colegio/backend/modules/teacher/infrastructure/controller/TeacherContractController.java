package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.teacher.application.dto.teacher_contract.CreateTeacherContractRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_contract.TeacherContractResponse;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-contract")
@Tag(name = "Teacher Contrato")
public class TeacherContractController {

    private final TeacherContractUseCase teacherContractUseCase;

    @Operation(summary = "Obtener contratos de profesor por code con filtros")
    @GetMapping("/teacher/{code}")
    public ResponseEntity<Page<TeacherContractResponse>> findWithFilters(
            @PathVariable String code,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {

        return ResponseEntity.ok(teacherContractUseCase.findWithFilters(code, startDate, endDate, page, size, sort));
    }

    @Operation(summary = "Create a new teacher")
    @PostMapping("/{code}")
    public ResponseEntity<TeacherContract> create(
            @PathVariable String code,
            @Valid CreateTeacherContractRequest createTeacherContractRequest
    ) {
        return ResponseEntity.ok(teacherContractUseCase.createContract(createTeacherContractRequest,code));
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherContractResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherContractUseCase.findById(id));
    }

}
