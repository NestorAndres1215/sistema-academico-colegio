package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.userStory.UserStoryRequest;
import com.colegio.backend.domain.model.UserStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserStoryUseCase {

    UserStory save (UserStoryRequest userStoryRequest);

    Page<UserStory> findWithFilters(String email, Boolean status, String action, Pageable pageable);

    String findLastCode();

    UserStory activate (String id);

    UserStory deactivate (String id);
}
