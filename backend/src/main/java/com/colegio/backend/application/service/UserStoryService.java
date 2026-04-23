package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.userStory.UserStoryRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.model.UserStory;
import com.colegio.backend.domain.port.repository.UserStoryRepositoryPort;
import com.colegio.backend.domain.port.usecases.UserStoryUseCase;
import com.colegio.backend.domain.port.usecases.UserUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserStoryService implements UserStoryUseCase {

    private final UserStoryRepositoryPort repositoryPort;
    private final UserUseCase userUseCase;

    @Override
    public UserStory save(UserStoryRequest userStoryRequest) {
        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());
        User user = userUseCase.findByEmail(userStoryRequest.getEmail());
        UserStory userStory= UserStory.builder()
                .id(newCode)
                .action(userStoryRequest.getAction())
                .detail(userStoryRequest.getDetail())
                .status(Status.ACTIVE)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        return repositoryPort.save(userStory);
    }

    @Override
    public Page<UserStory> findWithFilters(String email, Boolean status, String action, Pageable pageable) {
        return repositoryPort.findWithFilters(email, status, action, pageable);
    }

    @Override
    public String findLastCode() {
        return repositoryPort.findLastCode();
    }

    @Override
    public UserStory activate(String id) {

        UserStory existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Story not found"));

        existing.setStatus(Status.ACTIVE);
        return repositoryPort.save(existing);
    }

    @Override
    public UserStory deactivate(String id) {
        UserStory existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Story not found"));

        existing.setStatus(Status.INACTIVE);
        return repositoryPort.save(existing);
    }
}
