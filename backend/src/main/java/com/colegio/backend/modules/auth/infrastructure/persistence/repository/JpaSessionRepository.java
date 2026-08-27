package com.colegio.backend.modules.auth.infrastructure.persistence.repository;

import com.colegio.backend.modules.auth.infrastructure.persistence.entity.SessionEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.projection.ActiveSessionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaSessionRepository extends JpaRepository<SessionEntity,Long> {

    Optional<SessionEntity> findByRefreshToken(String token);

    // LISTAR SESSIONES ACTIVAS POR ID

    @Query("""
        SELECT s
        FROM SessionEntity s
        WHERE s.user.id = :userId
        AND s.status = 'ACTIVE'
    """)
    Optional<SessionEntity> findActiveByUserId(@Param("userId") Long userId);

    //LISTAR TODAS LAS SESIONES

    @Query("""
        SELECT
            s.id AS sessionId,
            u.id AS userId,
            u.username AS username,
            u.email AS email,
            s.loginAt AS loginAt
        FROM SessionEntity s
        JOIN s.user u
        WHERE s.status = 'ACTIVE'
          AND (
                :username IS NULL
                OR :username = ''
                OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))
          )
    """)
    Page<ActiveSessionProjection> findActiveSessions(
            @Param("username") String username,
            Pageable pageable
    );

}
