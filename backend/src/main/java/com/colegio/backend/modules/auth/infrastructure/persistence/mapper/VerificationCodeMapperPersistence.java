package com.colegio.backend.modules.auth.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.VerificationCodeEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationCodeMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;

    public VerificationCode toDomain(VerificationCodeEntity entity) {

        if (entity == null) {
            return null;
        }

        return VerificationCode.builder()
                .id(entity.getId())
                .user(userMapperPersistence.toDomain(entity.getUser()))
                .verificationType(entity.getVerificationType())
                .verificationCode(entity.getVerificationCode())
                .generatedAt(entity.getGeneratedAt())
                .expiresAt(entity.getExpiresAt())
                .verified(entity.getVerified())
                .attempts(entity.getAttempts())
                .build();
    }

    public VerificationCodeEntity toEntity(VerificationCode domain) {

        if (domain == null) {
            return null;
        }

        return VerificationCodeEntity.builder()
                .id(domain.getId())
                .user(userMapperPersistence.toEntity(domain.getUser()))
                .verificationType(domain.getVerificationType())
                .verificationCode(domain.getVerificationCode())
                .generatedAt(domain.getGeneratedAt())
                .expiresAt(domain.getExpiresAt())
                .verified(domain.getVerified())
                .attempts(domain.getAttempts())
                .build();
    }
}