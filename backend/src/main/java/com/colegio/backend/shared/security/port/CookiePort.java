package com.colegio.backend.shared.security.port;

import jakarta.servlet.http.Cookie;

public interface CookiePort {

    String getValue(Cookie[] cookies, String cookieName);

    void saveJwt(String token);

    void deleteJwt();

}