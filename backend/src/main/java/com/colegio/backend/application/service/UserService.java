package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.auth.PasswordRequest;
import com.colegio.backend.domain.exception.BadRequestException;
import com.colegio.backend.domain.exception.ConflictException;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.Role;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.domain.port.usecases.RoleUseCase;
import com.colegio.backend.domain.port.usecases.UserUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepositoryPort repositoryPort;
    private final RoleUseCase roleUseCase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return repositoryPort.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
    }

    @Override
    public List<User> findByStatus(Boolean status) {
        return repositoryPort.findByStatus(status);
    }

    @Override
    public List<User> findByEmailAndStatus(String email, Boolean status) {
        return repositoryPort.findByEmailAndStatus(email,status);
    }

    @Override
    public User save(String id, String email, String password,String role) {

        if (repositoryPort.existsByEmail(email)) {
            throw new ConflictException("The email is already registered");
        }

        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());
        Role roleEntity = roleUseCase.findByName(role);
        User user=  User.builder()
                .id(newCode)
                .email(email)
                .password(passwordEncoder.encode(password))
                .status(true)
                .createdAt(LocalDateTime.now())
                .roles(Collections.singletonList(roleEntity))
                .build();

        return repositoryPort.save(user);
    }

    @Override
    public User update(String id, String email, String password, String role) {
        User existingUser = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        if (!existingUser.getEmail().equals(email) && repositoryPort.existsByEmail(email)) {
            throw new ConflictException("The email is already registered");
        }

        existingUser.setEmail(email);
        if (password != null && !password.isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(password));
        }

        Role roleEntity = roleUseCase.findByName(role);
        existingUser.setRoles(Collections.singletonList(roleEntity));

        existingUser.setUpdatedAt(LocalDateTime.now());

        return repositoryPort.save(existingUser);
    }

    @Override
    public User activateUser(String id) {

        User existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));

        existing.setStatus(true);
        return repositoryPort.save(existing);
    }

    @Override
    public User deactivateUser(String id) {
        User existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));

        existing.setStatus(false);
        return repositoryPort.save(existing);
    }

    @Override
    public User changePassword(String userId, PasswordRequest request) {

        User user = repositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new BadRequestException("New password must be different from the current password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        return repositoryPort.save(user);
    }
}
