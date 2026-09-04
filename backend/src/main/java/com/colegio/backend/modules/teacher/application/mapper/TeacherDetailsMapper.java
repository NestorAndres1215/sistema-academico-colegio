package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherDetailsMapper {

    public TeacherDetails toDomain(TeacherRequest request, Teacher teacher) {
        return TeacherDetails.builder()
                .teacher(teacher)
                .university(request.university())
                .graduationDate(request.graduationDate())
                .yearsOfExperience(request.yearsOfExperience())
                .curriculum("")
                .notes(request.notes())
                .createdAt(LocalDateTime.now())
                .build();
    }
}