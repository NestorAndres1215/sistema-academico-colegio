package com.colegio.backend.modules.user_history.domain.port.repository;

import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserHistoryRepositoryPort {

    Optional<UserHistory> findById(Long id);

    UserHistory save (UserHistory userHistory);

    Page<UserHistory> findWithFilters(
            String email,
            String status,
            String action,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            Pageable pageable
    );
}
