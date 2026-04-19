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
public class TeacherFeedback {

    private String id;
    private Teacher teacher;
    private Student student;
    private Integer score;
    private String comment;
    private Status status;
    private String period;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
