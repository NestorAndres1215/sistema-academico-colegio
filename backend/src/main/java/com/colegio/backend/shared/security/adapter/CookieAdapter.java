package com.colegio.backend.shared.security.adapter;

import com.colegio.backend.shared.security.port.CookiePort;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieAdapter implements CookiePort {

    private static final String JWT_COOKIE = "jwt";
    private static final int MAX_AGE = 7 * 24 * 60 * 60;
    private final HttpServletResponse response;

    @Override
    public String getValue(Cookie[] cookies, String cookieName) {

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    @Override
    public void saveJwt(String token) {
        saveCookie(JWT_COOKIE, token);
    }

    @Override
    public void deleteJwt() {
        deleteCookie(JWT_COOKIE);
    }


    private void saveCookie(String name, String value) {

        Cookie cookie = new Cookie(name, value);

        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_AGE);

        response.addCookie(cookie);
    }

    private void deleteCookie(String name) {

        Cookie cookie = new Cookie(name, "");

        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

}