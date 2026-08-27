package com.colegio.backend.modules.user.application.validator;

import com.colegio.backend.modules.auth.application.dto.PasswordRequest;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.shared.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidator {

    private final PasswordEncoder passwordEncoder;

    public void validateChangePassword(User user, PasswordRequest request) {
        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new BadRequestException("La contraseña actual es incorrecta.");
        }

        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new BadRequestException("La nueva contraseña no puede ser igual a la contraseña actual.");
        }

        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new BadRequestException("La confirmación de la contraseña no coincide.");
        }
    }
}

