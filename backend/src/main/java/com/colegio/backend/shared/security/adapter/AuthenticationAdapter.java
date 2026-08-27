package com.colegio.backend.shared.security.adapter;

import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.shared.exception.UnauthorizedException;
import com.colegio.backend.shared.security.port.AuthenticationPort;
import com.colegio.backend.shared.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public User authenticate(String login, String password) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(login, password)
                    );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            assert userDetails != null;
            return userDetails.getUser();

        } catch (AuthenticationException ex) {
            throw new UnauthorizedException("Usuario o contraseña incorrectos");
        }
    }
}