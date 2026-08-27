package com.colegio.backend.modules.auth.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.RefreshToken;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.RefreshTokenEntity;

import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;

    public RefreshToken toDomain(RefreshTokenEntity entity) {
        if (entity == null) {
            return null;
        }

        return RefreshToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .user(userMapperPersistence.toDomain(entity.getUserEntity()))
                .status(entity.getStatus())
                .expiresAt(entity.getExpiresAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public RefreshTokenEntity toEntity(RefreshToken domain) {
        if (domain == null) {
            return null;
        }

        return RefreshTokenEntity.builder()
                .id(domain.getId())
                .token(domain.getToken())
                .userEntity(userMapperPersistence.toEntity(domain.getUser()))
                .status(domain.getStatus())
                .expiresAt(domain.getExpiresAt())
                .createdAt(domain.getCreatedAt())
                .build();
    }
}