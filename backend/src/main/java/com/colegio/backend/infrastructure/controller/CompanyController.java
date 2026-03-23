package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.port.usecases.CompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get company by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<Company> getByName(@PathVariable String name) {
        return ResponseEntity.ok(companyUseCase.findByName(name));
    }

    @Operation(summary = "Create a new company")
    @PostMapping
    public ResponseEntity<Company> create(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyUseCase.save(companyRequest));
    }

    @Operation(summary = "Update an existing company")
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable String id,
                                          @RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyUseCase.update(id,companyRequest));
    }
}
