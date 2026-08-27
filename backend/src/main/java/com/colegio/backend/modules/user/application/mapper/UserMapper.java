package com.colegio.backend.modules.user.application.mapper;

import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.shared.constant.StatusConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getStatus(),
                getRole(user)
        );
    }

    public User toDomain(
            String email,
            String username,
            String password,
            Role role
    ) {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .status(StatusConstants.ACTIVE)
                .createdAt(LocalDateTime.now())
                .roles(Collections.singletonList(role))
                .build();
    }

    public User updateDomain(
            User user,
            String email,
            String username,
            Role role
    ) {
        user.setEmail(email);
        user.setUsername(username);
        user.setRoles(Collections.singletonList(role));
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    private String getRole(User user) {
        return user.getRoles()
                .stream()
                .findFirst()
                .map(Role::getName)
                .orElse(null);
    }
}