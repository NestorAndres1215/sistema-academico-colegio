package com.colegio.backend.shared.security.port;

import java.util.List;

public interface TokenProviderPort {

    String generateToken(Long userId, List<String> roles);

    String extractUserId(String token);

    boolean validateToken(String token, String userId);

}