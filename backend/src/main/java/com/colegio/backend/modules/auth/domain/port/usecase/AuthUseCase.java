package com.colegio.backend.modules.auth.domain.port.usecase;

import com.colegio.backend.modules.auth.application.dto.LoginRequest;
import com.colegio.backend.modules.auth.application.dto.TokenResponse;
import com.colegio.backend.modules.user.application.dto.UserResponse;

public interface AuthUseCase {

    UserResponse currentUser(String username);

    TokenResponse login(LoginRequest request);

    void logout(String jwt);

}
