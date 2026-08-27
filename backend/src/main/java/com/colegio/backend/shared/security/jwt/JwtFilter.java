package com.colegio.backend.shared.security.jwt;

import com.colegio.backend.shared.security.port.CookiePort;
import com.colegio.backend.shared.security.service.TokenAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenAuthenticationService authenticationService;
    private final CookiePort cookiePort;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        String token = cookiePort.getValue(cookies, "jwt");

        if (token == null || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }


        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = authenticationService.authenticate(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }


        filterChain.doFilter(request, response);

    }

    private String getTokenFromCookie(Cookie[] cookies) {

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {

            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
