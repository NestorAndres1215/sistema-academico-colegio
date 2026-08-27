package com.colegio.backend.shared.security.port;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenAuthenticationPort {

    UserDetails authenticate(String token);
}
