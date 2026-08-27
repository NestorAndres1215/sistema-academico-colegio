package com.colegio.backend.modules.user.application.validator;

import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.shared.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepositoryPort userRepositoryPort;

    public void validateUserDoesNotExist(String email, String username) {

        if (userRepositoryPort.existsByEmail(email)) {
            throw new ConflictException("El correo ya esta registrado");
        }

        if (userRepositoryPort.existsByUsername(username)) {
            throw new ConflictException("El usuario ya esta registrado");
        }
    }

    public void validateUserUpdate(User existingUser, String email, String username) {

        if (!existingUser.getEmail().equals(email)
                && userRepositoryPort.existsByEmail(email)) {
            throw new ConflictException("El correo ya esta registrado");
        }

        if (!existingUser.getUsername().equals(username)
                && userRepositoryPort.existsByUsername(username)) {
            throw new ConflictException("El usuario ya esta registrado");
        }
    }
}