package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractReportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-report")
@Tag(name = "Teacher Reporte")
public class TeacherReportController {

    private final TeacherContractReportUseCase teacherReportUseCase;

    @Operation(summary = "Descargar contrato del profesor en PDF")
    @GetMapping("/contract/{contractId}/pdf")
    public ResponseEntity<byte[]> downloadContractPdf(@PathVariable Long contractId) {

        byte[] pdf = teacherReportUseCase.generateContractPdf(contractId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=contrato-" + contractId + ".pdf")
                .body(pdf);
    }

}
