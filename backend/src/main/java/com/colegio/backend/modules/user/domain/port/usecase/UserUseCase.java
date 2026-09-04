package com.colegio.backend.modules.user.domain.port.usecase;

import com.colegio.backend.modules.user.application.dto.PasswordRequest;
import com.colegio.backend.modules.user.application.dto.UpdatePasswordRequest;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserUseCase {

    UserResponse findByEmail(String email);

    UserResponse findById(Long id);

    List<UserResponse> search(String search);

    Page<UserResponse> getByStatus(String status, String search, Pageable pageable);

    User save(String email, String username, String password, String role);

    User update(Long id, String email, String username, String role);

    User activateUser (Long id);

    User deactivateUser (Long id);

    User changePassword(Long userId, PasswordRequest passwordRequest);

    User updateChangePassword(Long userId, UpdatePasswordRequest updatePasswordRequest);

}