package com.colegio.backend.modules.auth.application.mapper;

import com.colegio.backend.modules.auth.application.dto.TokenResponse;
import com.colegio.backend.modules.auth.domain.model.RefreshToken;
import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.user.domain.model.User;

import com.colegio.backend.shared.constant.StatusConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RefreshTokenMapper {

    public TokenResponse toResponse(String token, User user) {
        return new TokenResponse(
                "Login correcto",
                token,
                user.getUsername(),
                user.getEmail(),
                getPrimaryRole(user),
                "7 días"
        );
    }

    public RefreshToken toDomain(String token, User user) {

        return RefreshToken.builder()
                .token(token)
                .user(user)
                .status(StatusConstants.ACTIVE)
                .expiresAt(LocalDateTime.now().plusDays(7))
                .createdAt(LocalDateTime.now())
                .build();
    }

    private String getPrimaryRole(User user) {

        return user.getRoles()
                .stream()
                .findFirst()
                .map(Role::getName)
                .orElse(null);
    }
}