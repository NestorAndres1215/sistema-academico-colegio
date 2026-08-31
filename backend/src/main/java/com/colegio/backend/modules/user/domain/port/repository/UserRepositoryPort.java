package com.colegio.backend.modules.user.domain.port.repository;


import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Page<User> getByStatus(String status, String search, Pageable pageable);

    List<User> search(String search, int limit);

    List<User> findRandom(int limit);

    User save (User user);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}