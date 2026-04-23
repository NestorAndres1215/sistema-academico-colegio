package com.colegio.backend.domain.model;


import com.colegio.backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStory {

    private String id;
    private String action;
    private String detail;
    private LocalDateTime createdAt;
    private Status status;
    private User user;
}
