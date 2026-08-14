package com.colegio.backend.shared.security.handler;

import com.colegio.backend.shared.response.ErrorResponse;
import com.colegio.backend.shared.response.ErrorResponseFactory;
import com.colegio.backend.shared.security.response.JsonResponseWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final JsonResponseWriter jsonResponseWriter;
    private final ErrorResponseFactory errorResponseFactory;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException ex
    ) throws IOException {

        ErrorResponse error = errorResponseFactory.create(
                "Access denied.",
                HttpStatus.FORBIDDEN
        );

        response.setStatus(HttpStatus.FORBIDDEN.value());
        jsonResponseWriter.write(response, error);
    }

}