package com.colegio.backend.modules.auth.infrastructure.controller;

import com.colegio.backend.modules.auth.application.dto.LoginRequest;
import com.colegio.backend.modules.auth.application.dto.TokenResponse;
import com.colegio.backend.modules.auth.domain.port.usecase.AuthUseCase;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthUseCase authUseCase;

    @Operation(summary = "Generate authentication token")
    @PostMapping("/generate-token")
    public ResponseEntity<TokenResponse> generarToken(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authUseCase.login(request));
    }

    @Operation(summary = "Get currently authenticated user")
    @GetMapping("/current-user")
    public ResponseEntity<UserResponse> getCurrentUser(
            Authentication authentication
    ) {
        return ResponseEntity.ok(authUseCase.currentUser(authentication.getName()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "jwt", required = false) String jwt) {
        authUseCase.logout(jwt);
        return ResponseEntity.ok().build();
    }

}
