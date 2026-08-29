package com.colegio.backend.modules.user.application.service;

import com.colegio.backend.modules.auth.application.dto.PasswordRequest;
import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.domain.port.usecase.RoleUseCase;
import com.colegio.backend.modules.user.application.dto.UserResponse;
import com.colegio.backend.modules.user.application.mapper.UserMapper;
import com.colegio.backend.modules.user.application.validator.PasswordValidator;
import com.colegio.backend.modules.user.application.validator.UserValidator;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.modules.user.domain.port.usecase.UserUseCase;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final RoleUseCase roleUseCase;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final PasswordValidator passwordValidator;

    @Override
    public UserResponse findByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Correo electrónico no encontrado"));
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.toResponse(findUserById(id));
    }

    @Override
    public List<UserResponse> findByStatus(String status) {

        return userRepositoryPort.findByStatus(status)
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByEmailAndStatus(String email, String status) {

        return userRepositoryPort.findByEmailAndStatus(email, status)
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> search(String search) {

        List<User> users;

        if (search == null || search.isBlank()) {
            users = userRepositoryPort.findRandom(5);
        } else {
            users = userRepositoryPort.search(search.trim(), 5);
        }

        return users.stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Page<UserResponse> getByStatus(String status, String search, Pageable pageable) {

        return userRepositoryPort
                .getByStatus(status, search,pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public User save(String email, String username, String password, String role) {

        userValidator.validateUserDoesNotExist(email, username);

        Role roleModel = roleUseCase.findByName(role);

        User user = userMapper.toDomain(
                email,
                username,
                passwordEncoder.encode(password),
                roleModel
        );

        return userRepositoryPort.save(user);
    }

    @Override
    public User update(Long id, String email, String username, String role) {
        User existingUser = findUserById(id);

        userValidator.validateUserUpdate(
                existingUser,
                email,
                username
        );

        Role roleModel = roleUseCase.findByName(role);

        userMapper.updateDomain(
                existingUser,
                email,
                username,
                roleModel
        );

        return userRepositoryPort.save(existingUser);
    }

    @Override
    public User activateUser(Long id) {
        return updateStatus(id, StatusConstants.ACTIVE);
    }

    @Override
    public User deactivateUser(Long id) {
        return updateStatus(id, StatusConstants.INACTIVE);
    }

    @Override
    public User blockedUser(Long id) {
        return updateStatus(id, StatusConstants.BLOCKED);
    }

    private User updateStatus(Long id, String status) {

        User user = findUserById(id);

        user.setStatus(status);

        return userRepositoryPort.save(user);
    }

    @Override
    public User changePassword(Long userId, PasswordRequest request) {

        User user = findUserById(userId);

        passwordValidator.validateChangePassword(user, request);

        user.setPassword(passwordEncoder.encode(request.newPassword()));

        return userRepositoryPort.save(user);
    }

    private User findUserById(Long id) {

        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
