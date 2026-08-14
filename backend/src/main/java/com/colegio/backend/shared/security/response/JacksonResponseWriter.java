package com.colegio.backend.shared.security.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JacksonResponseWriter  implements  JsonResponseWriter{

    private final ObjectMapper objectMapper;

    @Override
    public void write(
            HttpServletResponse response,
            Object body
    ) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), body);

    }
}