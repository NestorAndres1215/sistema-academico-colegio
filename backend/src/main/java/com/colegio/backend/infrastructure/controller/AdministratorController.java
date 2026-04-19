package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.port.usecases.AdministratorUseCase;
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
@RequestMapping("/administrator")
@Tag(name = "Administrator")
public class AdministratorController {

    private final AdministratorUseCase administratorUseCase;

    @Operation(summary = "Get administrator by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Administrator> findById(@PathVariable String id) {
        return ResponseEntity.ok(administratorUseCase.findById(id));
    }

    @Operation(summary = "Create administrator")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Administrator> save(
            @RequestPart("file") MultipartFile file,
            @Valid @RequestPart("user") AdministratorRequest request) {
        return ResponseEntity.ok(administratorUseCase.save(file,request));
    }

    @Operation(summary = "Update administrator by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Administrator> update(@PathVariable String id, @Valid @RequestBody AdministratorRequest request) {
        return ResponseEntity.ok(administratorUseCase.update(id, request));
    }

    @Operation(summary = "Get all administrators")
    @GetMapping
    public ResponseEntity<Page<Administrator>> getByStatus(
            @RequestParam boolean status,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search
    ) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(administratorUseCase.getByStatus(status, search, pageable));

    }

    @Operation(summary = "Deactivate admin")
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Administrator> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(administratorUseCase.deactivate(id));
    }

    @Operation(summary = "Activate admin")
    @PutMapping("/activate/{id}")
    public ResponseEntity<Administrator> activate(@PathVariable String id) {
        return ResponseEntity.ok(administratorUseCase.activate(id));
    }
}
