package com.colegio.backend.modules.user.infrastructure.controller;

import com.colegio.backend.modules.user.application.dto.*;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.usecase.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {

    private final UserUseCase userUseCase;

    @Operation(summary = "Get all administrators")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {

        return ResponseEntity.ok(
                userUseCase.getByStatus(
                        status,
                        search,
                        PageRequest.of(page, size)
                )
        );

    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> search(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(userUseCase.search(search));
    }

    @Operation(summary = "Create a new position")
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(
                userUseCase.save(
                        createUserRequest.email(),
                        createUserRequest.username(),
                        createUserRequest.password(),
                        createUserRequest.role()
                )
        );
    }

    @Operation(summary = "Update user")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        return ResponseEntity.ok(
                userUseCase.update(
                        id,
                        updateUserRequest.email(),
                        updateUserRequest.username(),
                        updateUserRequest.role()
                )
        );
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.findById(id));
    }

    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userUseCase.findByEmail(email));
    }

    @Operation(summary = "Change user password")
    @PostMapping("/{id}/change-password")
    public ResponseEntity<User> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordRequest passwordRequest
    ) {
        return ResponseEntity.ok(userUseCase.changePassword(id, passwordRequest));
    }


    @Operation(summary = "Update Change user password")
    @PostMapping("/{id}/update-change-password")
    public ResponseEntity<User> updateChangePassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest
    ) {
        return ResponseEntity.ok(userUseCase.updateChangePassword(id, updatePasswordRequest));
    }

    @Operation(summary = "Activate admin")
    @PutMapping("/activate/{id}")
    public ResponseEntity<User> activate(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.activate(id));
    }

    @Operation(summary = "Deactivate admin")
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<User> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.deactivate(id));
    }

}