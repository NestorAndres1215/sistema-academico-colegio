package com.colegio.backend.shared.security.jwt;

import com.colegio.backend.shared.security.config.JwtProperties;
import com.colegio.backend.shared.security.port.TokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAdapter implements TokenProviderPort {

    private final JwtProperties properties;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(Long userId, List<String> roles) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("id", userId)
                .claim("roles",roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(properties.expirationMs())))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractUserId(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    @Override
    public boolean validateToken(String token, String userId) {
        Claims claims = extractAllClaims(token);
        return userId.equals(claims.getSubject())
                && claims.getExpiration()
                .after(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}