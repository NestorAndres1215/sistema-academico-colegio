package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.auth.LoginRequest;
import com.colegio.backend.application.dto.auth.TokenResponse;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.domain.port.usecases.AuthUseCase;
import com.colegio.backend.infrastructure.security.JwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort repositoryPort;
    private final JwtAdapter jwtUtil;

    @Override
    public User currentUser(Authentication authentication) {

        String username = authentication.getName();

        return repositoryPort.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }


    @Override
    public User authenticate(LoginRequest request) {
        return repositoryPort.findByEmail(request.getLogin())
                                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public String generateToken(User user) {
        return jwtUtil.generateToken(user);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        User login = authenticate(request);
        String token = generateToken(login);

        return TokenResponse.builder().token(token).build();
    }
}
