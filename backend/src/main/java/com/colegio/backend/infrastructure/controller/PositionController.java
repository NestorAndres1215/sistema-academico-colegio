package com.colegio.backend.infrastructure.controller;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.application.dto.position.PositionRequest;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.model.Position;
import com.colegio.backend.domain.port.usecases.CompanyUseCase;
import com.colegio.backend.domain.port.usecases.PositionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/position")
@Tag(name = "Position")
public class PositionController {

    private final PositionUseCase positionUseCase;

    @Operation(summary = "Get all positions")
    @GetMapping
    public ResponseEntity<List<Position>> getAll() {
        return ResponseEntity.ok(positionUseCase.findAll());
    }

    @Operation(summary = "Get position by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable String id) {
        return ResponseEntity.ok(positionUseCase.findById(id));
    }

    @Operation(summary = "Get company by position")
    @GetMapping("/name/{name}")
    public ResponseEntity<Position> getByName(@PathVariable String name) {
        return ResponseEntity.ok(positionUseCase.findByName(name));
    }

    @Operation(summary = "Create a new position")
    @PostMapping
    public ResponseEntity<Position> create(@RequestBody PositionRequest positionRequest) {
        return ResponseEntity.ok(positionUseCase.save(positionRequest));
    }

    @Operation(summary = "Update an existing position")
    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable String id,
                                           @RequestBody PositionRequest positionRequest) {
        return ResponseEntity.ok(positionUseCase.update(id,positionRequest));
    }

}
