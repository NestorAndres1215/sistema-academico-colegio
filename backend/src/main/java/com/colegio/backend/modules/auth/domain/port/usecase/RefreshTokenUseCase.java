package com.colegio.backend.modules.auth.domain.port.usecase;


import com.colegio.backend.modules.auth.domain.model.RefreshToken;

public interface RefreshTokenUseCase {

    RefreshToken save(Long userId, String jwt);

    void invalidateToken(String jwt);

}