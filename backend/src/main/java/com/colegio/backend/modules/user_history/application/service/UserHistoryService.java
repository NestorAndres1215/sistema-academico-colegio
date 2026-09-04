package com.colegio.backend.modules.user_history.application.service;

import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryRequest;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import com.colegio.backend.modules.user_history.application.mapper.UserHistoryMapper;
import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import com.colegio.backend.modules.user_history.domain.port.repository.UserHistoryRepositoryPort;
import com.colegio.backend.modules.user_history.domain.port.usecase.UserHistoryUseCase;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserHistoryService implements UserHistoryUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserHistoryRepositoryPort userHistoryRepositoryPort;
    private final UserHistoryMapper userHistoryMapper;


    @Override
    public UserHistory save(UserHistoryRequest request) {

        User user = findUserByEmail(request.email());

        UserHistory history = userHistoryMapper.toDomain(request, user);

        history.setStatus(StatusConstants.ACTIVE);
        history.setCreatedAt(LocalDateTime.now());

        return userHistoryRepositoryPort.save(history);
    }

    @Override
    public Page<UserHistoryResponse> findWithFilters(
            String email,
            String status,
            String action,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            int page,
            int size,
            String sort
    ) {

        Sort sortOrder = Sort.by(
                sort.equalsIgnoreCase("asc")
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC,
                "createdAt"
        );

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return userHistoryRepositoryPort
                .findWithFilters(email, status, action, dateFrom, dateTo, pageable)
                .map(userHistoryMapper::toResponse);
    }

    @Override
    public UserHistoryResponse findById(Long id) {
        return userHistoryMapper.toResponse(findUserHistoryById(id));
    }

    @Override
    public UserHistory activate(Long id) {
        return updateStatus(id, StatusConstants.ACTIVE);
    }

    @Override
    public UserHistory deactivate(Long id) {
        return updateStatus(id, StatusConstants.INACTIVE);
    }

    private UserHistory updateStatus(Long id, String status) {
        UserHistory userStory = findUserHistoryById(id);
        userStory.setStatus(status);
        return userHistoryRepositoryPort.save(userStory);
    }

    private UserHistory findUserHistoryById(Long id) {
        return userHistoryRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Historial no encontrado"));
    }

    private User findUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }
}
