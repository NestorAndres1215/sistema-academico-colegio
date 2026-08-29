package com.colegio.backend.modules.user_history.application.mapper;


import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryRequest;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import org.springframework.stereotype.Component;

@Component
public class UserHistoryMapper {

    public UserHistory toDomain(UserHistoryRequest request, User user) {

        return UserHistory.builder()
                .action(request.action())
                .detail(request.detail())
                .module(request.module())
                .user(user)
                .build();
    }

    public UserHistoryResponse toResponse(UserHistory history) {

        return new UserHistoryResponse(
                history.getId(),
                history.getAction(),
                history.getDetail(),
                history.getModule(),
                history.getCreatedAt().toLocalDate(),
                history.getCreatedAt().toLocalTime(),
                history.getStatus(),
                history.getUser().getUsername(),
                history.getUser().getEmail()
        );
    }
}