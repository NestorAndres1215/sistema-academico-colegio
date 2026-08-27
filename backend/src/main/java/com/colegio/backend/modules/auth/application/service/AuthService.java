package com.colegio.backend.modules.auth.application.service;


import com.colegio.backend.modules.auth.application.dto.LoginRequest;
import com.colegio.backend.modules.auth.application.dto.TokenResponse;
import com.colegio.backend.modules.auth.application.mapper.RefreshTokenMapper;
import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.domain.port.usecase.AuthUseCase;
import com.colegio.backend.modules.auth.domain.port.usecase.RefreshTokenUseCase;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import com.colegio.backend.modules.user.application.mapper.UserMapper;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.shared.exception.NotFoundException;
import com.colegio.backend.shared.security.port.AuthenticationPort;
import com.colegio.backend.shared.security.port.CookiePort;
import com.colegio.backend.shared.security.port.TokenProviderPort;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final UserMapper userMapper;
    private final RefreshTokenMapper refreshTokenMapper;
    private final RefreshTokenUseCase tokenService;
    private final CookiePort cookiePort;
    private final AuthenticationPort authenticationPort;

    @Override
    public UserResponse currentUser(String username) {
        User user = findUser(username);
        return userMapper.toResponse(user);
    }


    @Override
    public TokenResponse login(LoginRequest request) {

        User user = authenticationPort.authenticate(request.login(), request.password());

        String token = generateToken(user);

        tokenService.save(user.getId(), token);
        cookiePort.saveJwt(token);

        return refreshTokenMapper.toResponse(token, user);
    }


    @Override
    public void logout(String jwt) {

        if (jwt != null) {
            tokenService.invalidateToken(jwt);
        }

        cookiePort.deleteJwt();
    }

    private String generateToken(User user) {
        return tokenProviderPort.generateToken(
                user.getId(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
    }

    private User findUser(String login) {
        return userRepositoryPort.findByUsername(login)
                .orElseGet(() ->
                        userRepositoryPort.findByEmail(login)
                                .orElseThrow(() ->
                                        new NotFoundException("Usuario no encontrado")));
    }

}