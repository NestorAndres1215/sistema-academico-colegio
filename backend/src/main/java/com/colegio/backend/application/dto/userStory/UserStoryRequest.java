package com.colegio.backend.application.dto.userStory;

import lombok.Data;

@Data
public class UserStoryRequest {

    private String action;
    private String detail;
    private String email;
}
