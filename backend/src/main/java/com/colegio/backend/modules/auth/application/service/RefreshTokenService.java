package com.colegio.backend.modules.auth.application.service;

import com.colegio.backend.modules.auth.application.mapper.RefreshTokenMapper;
import com.colegio.backend.modules.auth.domain.model.RefreshToken;
import com.colegio.backend.modules.auth.domain.port.repository.RefreshTokenRepositoryPort;
import com.colegio.backend.modules.auth.domain.port.usecase.RefreshTokenUseCase;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public RefreshToken save(Long userId, String jwt) {
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        RefreshToken refreshToken = refreshTokenMapper.toDomain(jwt, user);

        return refreshTokenRepositoryPort.save(refreshToken);
    }



    public void invalidateToken(String jwt) {

        RefreshToken token = refreshTokenRepositoryPort.findByToken(jwt)
                .orElseThrow(() -> new NotFoundException("Token not found"));

        token.setStatus(StatusConstants.INACTIVE);
        refreshTokenRepositoryPort.save(token);
    }
}