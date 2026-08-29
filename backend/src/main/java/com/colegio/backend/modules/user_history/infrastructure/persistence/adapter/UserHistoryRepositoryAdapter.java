package com.colegio.backend.modules.user_history.infrastructure.persistence.adapter;

import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import com.colegio.backend.modules.user_history.domain.port.repository.UserHistoryRepositoryPort;
import com.colegio.backend.modules.user_history.infrastructure.persistence.entity.UserHistoryEntity;
import com.colegio.backend.modules.user_history.infrastructure.persistence.mapper.UserHistoryMapperPersistence;
import com.colegio.backend.modules.user_history.infrastructure.persistence.repository.JpaUserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHistoryRepositoryAdapter implements UserHistoryRepositoryPort {

    private final JpaUserHistoryRepository jpaUserHistoryRepository;
    private final UserHistoryMapperPersistence userHistoryMapperPersistence;


    @Override
    public Optional<UserHistory> findById(Long id) {
        return jpaUserHistoryRepository.findById(id)
                .map(userHistoryMapperPersistence::toDomain);
    }

    @Override
    public UserHistory save(UserHistory userHistory) {
        UserHistoryEntity entity = userHistoryMapperPersistence.toEntity(userHistory);
        UserHistoryEntity saved = jpaUserHistoryRepository.save(entity);
        return userHistoryMapperPersistence.toDomain(saved);
    }

    @Override
    public Page<UserHistory> findWithFilters(
            String email,
            String status,
            String action,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            Pageable pageable
    ) {
        return jpaUserHistoryRepository.findWithFilters(
                email,
                status,
                action,
                dateFrom,
                dateTo,
                pageable
                )
                .map(userHistoryMapperPersistence::toDomain);
    }
}
