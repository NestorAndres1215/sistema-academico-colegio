package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.auth.LoginRequest;
import com.colegio.backend.application.dto.auth.TokenResponse;
import com.colegio.backend.domain.model.User;
import org.springframework.security.core.Authentication;

public interface AuthUseCase {

    User actualUsuario(Authentication authentication);

    User authenticate(LoginRequest request);

    String generateToken(User user);

    TokenResponse login(LoginRequest request);
}
