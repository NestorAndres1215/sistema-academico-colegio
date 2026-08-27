package com.colegio.backend.modules.auth.infrastructure.persistence.adapter;

import com.colegio.backend.modules.auth.domain.model.RefreshToken;
import com.colegio.backend.modules.auth.domain.port.repository.RefreshTokenRepositoryPort;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.RefreshTokenEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.RefreshTokenMapperPersistence;
import com.colegio.backend.modules.auth.infrastructure.persistence.repository.JpaRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepositoryPort {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final RefreshTokenMapperPersistence refreshTokenMapperPersistence;

    @Override
    public RefreshToken save(RefreshToken token) {

        RefreshTokenEntity refreshTokenEntity = refreshTokenMapperPersistence.toEntity(token);

        RefreshTokenEntity saved = jpaRefreshTokenRepository.save(refreshTokenEntity);

        return refreshTokenMapperPersistence.toDomain(saved);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {

        return jpaRefreshTokenRepository.findByToken(token)
                .map(refreshTokenMapperPersistence::toDomain);
    }
}