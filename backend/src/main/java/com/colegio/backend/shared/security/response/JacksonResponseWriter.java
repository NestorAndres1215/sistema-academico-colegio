package com.colegio.backend.shared.security.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JacksonResponseWriter implements JsonResponseWriter {

    private static final String CONTENT_TYPE = "application/json";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private final ObjectMapper objectMapper;

    @Override
    public void write(
            HttpServletResponse response,
            Object body
    ) throws IOException {

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);

        objectMapper.writeValue(response.getWriter(), body);
    }
}