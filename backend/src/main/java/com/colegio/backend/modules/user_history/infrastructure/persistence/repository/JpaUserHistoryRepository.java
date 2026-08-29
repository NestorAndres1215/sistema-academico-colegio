package com.colegio.backend.modules.user_history.infrastructure.persistence.repository;

import com.colegio.backend.modules.user_history.infrastructure.persistence.entity.UserHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface JpaUserHistoryRepository extends JpaRepository<UserHistoryEntity,Long> {

    @Query("""
        SELECT u
        FROM UserStoryEntity u
        WHERE u.user.email = :email
          AND (:status IS NULL OR u.status = :status)
          AND (:action IS NULL OR LOWER(u.action) LIKE LOWER(CONCAT('%', :action, '%')))
          AND (:dateFrom IS NULL OR u.createdAt >= :dateFrom)
          AND (:dateTo IS NULL OR u.createdAt <= :dateTo)
    """)
    Page<UserHistoryEntity> findWithFilters(
            String email,
            String status,
            String action,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            Pageable pageable
    );

}
