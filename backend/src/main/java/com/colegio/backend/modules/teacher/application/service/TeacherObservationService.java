package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.teacher.application.dto.teacher_observation.CreateTeacherObservationRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_observation.TeacherObservationResponse;
import com.colegio.backend.modules.teacher.application.mapper.TeacherObservationMapper;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherObservationRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherObservationUseCase;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class TeacherObservationService implements TeacherObservationUseCase {

    private final TeacherObservationMapper teacherObservationMapper;
    private final TeacherObservationRepositoryPort teacherObservationRepositoryPort;
    private final TeacherRepositoryPort teacherRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;


    @Override
    public TeacherObservationResponse findByTeacher_Id(Long id) {

        TeacherObservation teacherObservation = findByTeacherId(id);

        return teacherObservationMapper.toResponse(teacherObservation);
    }

    @Override
    public TeacherObservationResponse findById(Long id) {

        TeacherObservation teacherObservation = findByTeacherObservationId(id);

        return teacherObservationMapper.toResponse(teacherObservation);
    }

    @Override
    public TeacherObservation save(CreateTeacherObservationRequest createTeacherObservationRequest) {

        Teacher teacher = findTeacher(createTeacherObservationRequest.teacher());
        User observer = findObserver(createTeacherObservationRequest.usuario());

        TeacherObservation observation =
                teacherObservationMapper.toDomain(createTeacherObservationRequest, teacher, observer);

        return teacherObservationRepositoryPort.save(observation);

    }

    @Override
    public Page<TeacherObservationResponse> findByFilters(String teacherCode, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return teacherObservationRepositoryPort.findByFilters(teacherCode, startDate,endDate, pageable)
                .map(teacherObservationMapper::toResponse);
    }

    private TeacherObservation findByTeacherId(Long teacherId) {
        return teacherObservationRepositoryPort.findByTeacher_Id(teacherId)
                .orElseThrow(() ->
                        new NotFoundException("Detalle del profesor no encontrado"));
    }

    private TeacherObservation findByTeacherObservationId(Long id) {
        return teacherObservationRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Detalle del profesor no encontrado"));
    }

    private Teacher findTeacher(String code) {
        return teacherRepositoryPort.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado"));
    }

    private User findObserver(String username) {
        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

}
