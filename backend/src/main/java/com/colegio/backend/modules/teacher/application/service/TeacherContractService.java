package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.dto.TeacherContractResponse;
import com.colegio.backend.modules.teacher.application.mapper.TeacherContractMapper;
import com.colegio.backend.modules.teacher.application.validator.TeacherContractValidator;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherContractRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TeacherContractService implements TeacherContractUseCase {

    private final TeacherContractRepositoryPort teacherContractRepositoryPort;
    private final TeacherContractValidator teacherContractValidator;
    private final TeacherContractMapper teacherContractMapper;

    @Override
    public TeacherContract create(TeacherContract teacherContract, Teacher teacher) {

        teacherContract.setTeacher(teacher);

        teacherContractValidator.validateContract(
                teacherContract.getContractType(),
                teacherContract.getStartDate(),
                teacherContract.getEndDate()
        );

        return teacherContractRepositoryPort.save(teacherContract);
    }

    @Override
    public TeacherContract activate(Long id) {
        return updateStatus(id, StatusConstants.ACTIVE);
    }

    @Override
    public TeacherContract deactivate(Long id) {
        return updateStatus(id, StatusConstants.INACTIVE);
    }

    @Override
    public Page<TeacherContractResponse> findWithFilters(String teacherCode, LocalDate startDate, LocalDate endDate, int page, int size, String sort) {

        Sort sortOrder = Sort.by(
                "asc".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC,
                "endDate"
        );

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return teacherContractRepositoryPort.findWithFilters(teacherCode, startDate, endDate, pageable)
                .map(teacherContractMapper::toResponse);
    }

    private TeacherContract updateStatus(Long id, String status) {

        TeacherContract teacherContract = findByTeacherId(id);

        teacherContract.setStatus(status);

        return teacherContractRepositoryPort.save(teacherContract);
    }

    private TeacherContract findByTeacherId(Long id) {
        return teacherContractRepositoryPort.findByTeacher_Id(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

}
