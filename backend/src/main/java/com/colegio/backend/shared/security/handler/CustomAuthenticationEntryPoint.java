package com.colegio.backend.shared.security.handler;

import com.colegio.backend.shared.response.ErrorResponse;
import com.colegio.backend.shared.response.ErrorResponseFactory;
import com.colegio.backend.shared.security.response.JsonResponseWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JsonResponseWriter jsonResponseWriter;
    private final ErrorResponseFactory errorResponseFactory;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        ErrorResponse error = errorResponseFactory.create(
                "Authentication required",
                HttpStatus.FORBIDDEN
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        jsonResponseWriter.write(response, error);
    }
}