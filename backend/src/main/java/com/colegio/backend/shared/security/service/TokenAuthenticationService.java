package com.colegio.backend.shared.security.service;

import com.colegio.backend.modules.auth.domain.model.RefreshToken;
import com.colegio.backend.modules.auth.domain.port.repository.RefreshTokenRepositoryPort;
import com.colegio.backend.shared.exception.BadRequestException;
import com.colegio.backend.shared.security.jwt.JwtAdapter;
import com.colegio.backend.shared.security.port.TokenAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService implements TokenAuthenticationPort {

    private final JwtAdapter jwtAdapter;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public UserDetails authenticate(String token) {
        String userId;

        RefreshToken tokenEntity = refreshTokenRepositoryPort.findByToken(token)
                .orElseThrow(() -> new BadRequestException("Token inválido"));

        try {
            userId = jwtAdapter.extractUserId(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Token inválido");
        }

        if (!jwtAdapter.validateToken(token, userId)) {
            throw new BadCredentialsException("Token inválido");
        }

        return userDetailsService.loadUserById(Long.valueOf(userId));
    }
}
