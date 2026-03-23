package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.domain.model.Role;
import com.colegio.backend.domain.port.usecases.RoleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
@Tag(name = "Role")
public class RoleController {

    private final RoleUseCase roleUseCase;


    @Operation(summary = "Get all roles")
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleUseCase.findAll());
    }

    @Operation(summary = "Get role by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable String id) {
        return ResponseEntity.ok(roleUseCase.findById(id));
    }

    @Operation(summary = "Get role by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {
        return ResponseEntity.ok(roleUseCase.findByName(name));
    }
}
