package com.colegio.backend.modules.user_history.domain.port.usecase;

import com.colegio.backend.modules.user_history.application.dto.UserHistoryRequest;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface UserHistoryUseCase {

    UserHistory save (UserHistoryRequest userHistoryRequest);

    Page<UserHistoryResponse> findWithFilters(
            String email,
            String status,
            String action,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            int page,
            int size,
            String sort
    );

    UserHistoryResponse findById (Long id);

    UserHistory activate (Long id);

    UserHistory deactivate (Long id);
}

