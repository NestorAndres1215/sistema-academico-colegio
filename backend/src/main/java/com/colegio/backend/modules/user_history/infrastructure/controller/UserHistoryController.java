package com.colegio.backend.modules.user_history.infrastructure.controller;

import com.colegio.backend.modules.user_history.application.dto.UserHistoryRequest;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import com.colegio.backend.modules.user_history.domain.port.usecase.UserHistoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-history")
@Tag(name = "User History")
public class UserHistoryController {

    private final UserHistoryUseCase userHistoryUseCase;

    @Operation(summary = "Obtener historial de usuarios con filtros")
    @GetMapping
    public ResponseEntity<Page<UserHistoryResponse>> findWithFilters(
            @RequestParam String email,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) LocalDateTime dateFrom,
            @RequestParam(required = false) LocalDateTime dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {

        return ResponseEntity.ok(
                userHistoryUseCase.findWithFilters(
                        email,
                        status,
                        action,
                        dateFrom,
                        dateTo,
                        page,
                        size,
                        sort
                )
        );
    }

    @Operation(summary = "Register a new user story")
    @PostMapping
    public ResponseEntity<UserHistory> save(@RequestBody UserHistoryRequest userHistoryRequest) {
        return ResponseEntity.ok(userHistoryUseCase.save(userHistoryRequest));
    }

    @Operation(summary = "Activate user story")
    @PutMapping("/{id}/activate")
    public ResponseEntity<UserHistory> activate(@PathVariable Long id) {
        return ResponseEntity.ok(userHistoryUseCase.activate(id));
    }

    @Operation(summary = "Deactivate user story")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<UserHistory> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(userHistoryUseCase.deactivate(id));
    }

}
