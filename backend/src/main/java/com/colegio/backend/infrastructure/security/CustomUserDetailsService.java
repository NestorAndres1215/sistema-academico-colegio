package com.colegio.backend.infrastructure.security;

import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = loginRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return new CustomUserDetails(user);
    }
}