package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.teacher.application.dto.TeacherContractResponse;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherUseCase;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-contract")
@Tag(name = "Teacher Contrato")
public class TeacherContractController {

    private final TeacherContractUseCase teacherContractUseCase;

    @Operation(summary = "Obtener contratos de profesor por code con filtros")
    @GetMapping("/{code}")
    public ResponseEntity<Page<TeacherContractResponse>> findWithFilters(
            @PathVariable String code,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {
try {
    return ResponseEntity.ok(
            teacherContractUseCase.findWithFilters(code, startDate, endDate, page, size, sort)
    );
} catch (Exception e) {
    e.printStackTrace();
    throw new RuntimeException(e);
}
    }

}
