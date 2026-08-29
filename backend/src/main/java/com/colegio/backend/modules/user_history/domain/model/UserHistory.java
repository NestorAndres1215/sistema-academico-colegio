package com.colegio.backend.modules.user_history.domain.model;

import com.colegio.backend.modules.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory {

    private Long id;
    private String action;
    private String detail;
    private String module;
    private LocalDateTime createdAt;
    private String status;
    private User user;

}
