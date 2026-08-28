package com.colegio.backend.shared.security.service;


import com.colegio.backend.shared.security.jwt.JwtAdapter;
import com.colegio.backend.shared.security.port.TokenAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService implements TokenAuthenticationPort {

    private final JwtAdapter jwtAdapter;

    private final CustomUserDetailsService userDetailsService;

    @Override
    public UserDetails authenticate(String token) {
        String userId;



        try {
            userId = jwtAdapter.extractUserId(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Token inválido");
        }

        if (!jwtAdapter.validateToken(token, userId)) {
            throw new BadCredentialsException("Token inválido");
        }

        return userDetailsService.loadUserById(Long.valueOf(userId));
    }
}
