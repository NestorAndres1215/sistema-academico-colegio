package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.port.usecases.AdministratorUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/administrator")
@Tag(name = "Administrator")
public class AdministratorContoller {

    private final AdministratorUseCase administratorUseCase;

    @Operation(summary = "Create administrator")
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AdministratorRequest request) {
        try {
            Administrator admin = administratorUseCase.save(request);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error"));
        }
    }

    @Operation(summary = "Update administrator by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Administrator> update(@PathVariable String id, @Valid @RequestBody AdministratorRequest request) {
        return ResponseEntity.ok(administratorUseCase.update(id, request));
    }

    @Operation(summary = "Get administrator by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Administrator> findById(@PathVariable String id) {
        return ResponseEntity.ok(administratorUseCase.findById(id));
    }

    @Operation(summary = "Get all administrators")
    @GetMapping
    public ResponseEntity<List<Administrator>> findAll() {
        return ResponseEntity.ok(administratorUseCase.findAll());
    }

    @Operation(summary = "Get administrator by DNI")
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Administrator> findByDni(@PathVariable String dni) {
        return ResponseEntity.ok(administratorUseCase.findByDni(dni));
    }

    @Operation(summary = "Get administrators by phone")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Administrator>> findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(administratorUseCase.findByPhone(phone));
    }

    @Operation(summary = "Get administrators by gender")
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Administrator>> findByGender(@PathVariable String gender) {
        return ResponseEntity.ok(administratorUseCase.findByGender(gender));
    }

    @Operation(summary = "Search administrators by first name")
    @GetMapping("/name")
    public ResponseEntity<List<Administrator>> findByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(administratorUseCase.findByFirstName(firstName));
    }

    @Operation(summary = "Search by first name and paternal last name")
    @GetMapping("/name-lastname")
    public ResponseEntity<List<Administrator>> findByFirstNameAndPaternalLastName(@RequestParam String firstName, @RequestParam String paternalLastName) {
        return ResponseEntity.ok(administratorUseCase.findByFirstNameAndPaternalLastName(firstName, paternalLastName));
    }

    @Operation(summary = "Search by full name")
    @GetMapping("/full-name")
    public ResponseEntity<List<Administrator>> findFullName(@RequestParam String firstName, @RequestParam String paternalLastName, @RequestParam String maternalLastName) {
        return ResponseEntity.ok(administratorUseCase.findByFirstNameAndPaternalLastNameAndMaternalLastName(firstName, paternalLastName, maternalLastName));
    }
}
