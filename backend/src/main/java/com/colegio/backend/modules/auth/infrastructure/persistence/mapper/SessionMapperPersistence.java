package com.colegio.backend.modules.auth.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.Session;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.SessionEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;
    private final RefreshTokenMapperPersistence refreshTokenMapperPersistence;

    public Session toDomain(SessionEntity sessionEntity){
        if(sessionEntity == null) return null;

        return  Session.builder()
                .id(sessionEntity.getId())
                .user(userMapperPersistence.toDomain(sessionEntity.getUser()))
                .refreshToken(refreshTokenMapperPersistence.toDomain(sessionEntity.getRefreshToken()))
                .status(sessionEntity.getStatus())
                .ipAddress(sessionEntity.getIpAddress())
                .location(sessionEntity.getLocation())
                .userAgent(sessionEntity.getUserAgent())
                .loginAt(sessionEntity.getLoginAt())
                .logoutAt(sessionEntity.getLogoutAt())
                .lastActivityAt(sessionEntity.getLastActivityAt())
                .build();

    }

    public SessionEntity toEntity(Session session) {
        if(session == null) return null;

        return SessionEntity.builder()
                .id(session.getId())
                .user(userMapperPersistence.toEntity(session.getUser()))
                .refreshToken(refreshTokenMapperPersistence.toEntity(session.getRefreshToken()))
                .status(session.getStatus())
                .ipAddress(session.getIpAddress())
                .location(session.getLocation())
                .userAgent(session.getUserAgent())
                .loginAt(session.getLoginAt())
                .logoutAt(session.getLogoutAt())
                .lastActivityAt(session.getLastActivityAt())
                .build();

    }

}