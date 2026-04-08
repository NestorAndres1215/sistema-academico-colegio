package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.UserStoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserStoryRepository extends JpaRepository<UserStoryEntity,String> {

    @Query("""
    SELECT u FROM UserStoryEntity u
    WHERE u.userEntity.email = :email
    AND (:status IS NULL OR u.status = :status)
    AND (:action IS NULL OR LOWER(u.action) LIKE LOWER(CONCAT('%', :action, '%')))
""")
    Page<UserStoryEntity> findWithFilters(
            @Param("email") String email,
            @Param("status") Boolean status,
            @Param("action") String action,
            Pageable pageable
    );

    @Query("SELECT MAX(c.id) FROM UserStoryEntity c")
    String findLastCode();

}
