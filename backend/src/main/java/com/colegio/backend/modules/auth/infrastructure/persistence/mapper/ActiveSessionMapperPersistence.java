package com.colegio.backend.modules.auth.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.ActiveSession;
import com.colegio.backend.modules.auth.infrastructure.persistence.projection.ActiveSessionProjection;
import org.springframework.stereotype.Component;

@Component
public class ActiveSessionMapperPersistence {

    public ActiveSession toDomain(ActiveSessionProjection projection) {
        return ActiveSession.builder()
                .sessionId(projection.getSessionId())
                .userId(projection.getUserId())
                .username(projection.getUsername())
                .email(projection.getEmail())
                .loginAt(projection.getLoginAt())
                .build();
    }
}