package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.port.usecases.CompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
@Tag(name = "Company")
public class CompanyController {

    private final CompanyUseCase companyUseCase;

    @Operation(summary = "Get all companies")
    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
            return ResponseEntity.ok(companyUseCase.findAll());
    }

    @Operation(summary = "Get company by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable String id) {
        return ResponseEntity.ok(companyUseCase.findById(id));
    }


    @Operation(summary = "Create a new company")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Company> create(
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("company") CompanyRequest request) throws Exception {
        return ResponseEntity.ok(companyUseCase.save(file,request));
    }

    @Operation(summary = "Update an existing company")
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable String id,
                                          @RequestPart("file") MultipartFile file,
                                          @Valid @RequestPart("company") CompanyRequest request) throws IOException {
        return ResponseEntity.ok(companyUseCase.update(id,file,request));
    }
}
