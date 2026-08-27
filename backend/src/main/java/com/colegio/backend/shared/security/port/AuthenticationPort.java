package com.colegio.backend.shared.security.port;

import com.colegio.backend.modules.user.domain.model.User;

public interface AuthenticationPort {

    User authenticate(String login, String password);

}