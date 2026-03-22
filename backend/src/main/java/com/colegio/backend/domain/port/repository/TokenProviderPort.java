package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.User;

public interface TokenProviderPort {
    String generateToken(User user);

    String extractUsername(String token);

    boolean validateToken(String token, String username);
}
