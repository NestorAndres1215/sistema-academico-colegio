package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    User findByEmail(String email);

    List<User> findByStatus(Boolean status);

    List<User> findByEmailAndStatus(String email, Boolean status);

    User save(String id , String email, String password, String role);

    User update(String id , String email, String password,String role);
}
