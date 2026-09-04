package com.colegio.backend.modules.user.infrastructure.persistence.repository;

import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
        SELECT DISTINCT u
        FROM UserEntity u
        JOIN u.roleEntities r
        WHERE (
            :status IS NULL
            OR :status = ''
            OR u.status = :status
        )
        AND (
            :search IS NULL
            OR :search = ''
            OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
        )
        AND r.name = 'ROLE_ADMIN'
        """)
    Page<UserEntity> searchByStatus(
            @Param("status") String status,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("""
        SELECT DISTINCT u
        FROM UserEntity u
        JOIN u.roleEntities r
        WHERE u.status = 'ACTIVE'
        AND r.name = 'ROLE_ADMIN'
        AND (
            LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
        )
        ORDER BY u.username ASC
        """)
    List<UserEntity> searchActive(
            @Param("search") String search,
            Pageable pageable
    );

    @Query(value = """
                SELECT *
                FROM user
                WHERE status = 'ACTIVE'
                ORDER BY RAND()
                LIMIT :limit
            """, nativeQuery = true)
    List<UserEntity> findRandom(@Param("limit") Pageable limit);

}