package com.colegio.backend.modules.companies.infrastructure.controller;


import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.companies.domain.port.usecase.CompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
@Tag(name = "Company")
public class CompanyController {

    private final CompanyUseCase companyUseCase;

    @Operation(summary = "Get company by Code")
    @GetMapping("/code/{code}")
    public ResponseEntity<CompanyResponse> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(companyUseCase.findByCode(code));
    }


    @Operation(summary = "Create a new company")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Company> create(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @Valid @RequestPart("company") CompanyRequest request
    ) throws Exception {
        return ResponseEntity.ok(companyUseCase.save(file, request));
    }

    @Operation(summary = "Update an existing company")
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("company") CompanyRequest request
    ) throws IOException {
        return ResponseEntity.ok(companyUseCase.update(id,file,request));
    }
}
