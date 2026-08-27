package com.colegio.backend.shared.security.response;


import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JsonResponseWriter {

    void write(HttpServletResponse response, Object body) throws IOException;

}