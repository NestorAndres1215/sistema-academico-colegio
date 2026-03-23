package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.UserEntity;
import com.colegio.backend.infrastructure.persistence.mapper.UserMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<User> findByStatus(Boolean status) {
        return repository.findByStatus(status).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<User> findByEmailAndStatus(String email, Boolean status) {
        return repository.findByEmailAndStatus(email, status).stream().map(mapper::toDomain).toList();
    }
}
