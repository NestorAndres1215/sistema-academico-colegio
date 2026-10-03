package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.teacher_observation.CreateTeacherObservationRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_observation.TeacherObservationResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherObservationMapper {

    public TeacherObservationResponse toResponse(TeacherObservation observation) {
        return new TeacherObservationResponse(
                observation.getId(),
                observation.getTeacher().getUser().getUsername(),
                observation.getTitle(),
                observation.getDescription(),
                observation.getObservationDate()
        );
    }

    public TeacherObservation toDomain(
            CreateTeacherObservationRequest request,
            Teacher teacher,
            User observer
    ) {
        return TeacherObservation.builder()
                .teacher(teacher)
                .observer(observer)
                .title(request.title())
                .description(request.description())
                .observationDate(request.observationDate())
                .createdAt(LocalDateTime.now())
                .build();
    }
}