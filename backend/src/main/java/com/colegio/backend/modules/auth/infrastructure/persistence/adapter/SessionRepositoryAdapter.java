package com.colegio.backend.modules.auth.infrastructure.persistence.adapter;

import com.colegio.backend.modules.auth.domain.model.ActiveSession;
import com.colegio.backend.modules.auth.domain.model.Session;
import com.colegio.backend.modules.auth.domain.port.repository.SessionRepositoryPort;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.SessionEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.ActiveSessionMapperPersistence;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.SessionMapperPersistence;
import com.colegio.backend.modules.auth.infrastructure.persistence.repository.JpaSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionRepositoryAdapter implements SessionRepositoryPort {

    private final JpaSessionRepository jpaSessionRepository;
    private final SessionMapperPersistence sessionMapperPersistence;
    private final ActiveSessionMapperPersistence activeSessionMapperPersistence;

    @Override
    public Optional<Session> findByRefreshToken(String token) {

        return jpaSessionRepository.findByRefreshToken(token)
                .map(sessionMapperPersistence::toDomain);
    }

    @Override
    public Session save(Session session) {

        SessionEntity entity = sessionMapperPersistence.toEntity(session);

        SessionEntity saved = jpaSessionRepository.save(entity);

        return sessionMapperPersistence.toDomain(saved);
    }

    @Override
    public Optional<Session> findActiveByUserId(Long userId) {

        return jpaSessionRepository.findActiveByUserId(userId)
                .map(sessionMapperPersistence::toDomain);
    }

    @Override
    public Optional<Session> findById(Long id) {

        return  jpaSessionRepository.findById(id)
                .map(sessionMapperPersistence::toDomain);
    }

    @Override
    public Page<ActiveSession> findActiveSessions(String search, Pageable pageable) {

        return jpaSessionRepository.findActiveSessions(search, pageable)
                .map(activeSessionMapperPersistence::toDomain);
    }

}