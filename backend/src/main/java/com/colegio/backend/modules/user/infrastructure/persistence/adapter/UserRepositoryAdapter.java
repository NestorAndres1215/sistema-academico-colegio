package com.colegio.backend.modules.user.infrastructure.persistence.adapter;

import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import com.colegio.backend.modules.user.infrastructure.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapperPersistence userMapperPersistence;

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userMapperPersistence::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(userMapperPersistence::toDomain);
    }

    @Override
    public Page<User> getByStatus(String status, String search, Pageable pageable) {
        return jpaUserRepository.searchByStatus(status, search, pageable)
                .map(userMapperPersistence::toDomain);
    }


    @Override
    public List<User> search(String search, int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        return jpaUserRepository.searchActive(search, pageable)
                .stream()
                .map(userMapperPersistence::toDomain)
                .toList();
    }

    @Override
    public List<User> findRandom(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        return jpaUserRepository.findRandom(pageable)
                .stream()
                .map(userMapperPersistence::toDomain)
                .toList();
    }

    @Override
    public User save(User user) {

        UserEntity entity = userMapperPersistence.toEntity(user);

        UserEntity saved = jpaUserRepository.save(entity);

        return userMapperPersistence.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userMapperPersistence::toDomain);
    }

}