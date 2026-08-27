package com.colegio.backend.modules.auth.domain.port.repository;

import com.colegio.backend.modules.auth.domain.model.ActiveSession;
import com.colegio.backend.modules.auth.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SessionRepositoryPort {

    Optional<Session> findByRefreshToken(String token);

    Session save (Session session);

    Optional<Session> findActiveByUserId(Long userId);

    Optional<Session> findById(Long id);

    Page<ActiveSession> findActiveSessions(String search, Pageable pageable);
}