package com.colegio.backend.modules.auth.domain.port.repository;

import com.colegio.backend.modules.auth.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepositoryPort {

    RefreshToken save (RefreshToken token);

    Optional<RefreshToken> findByToken(String token);

}