package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.validator.TeacherContractValidator;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherContractRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherContractService implements TeacherContractUseCase {

    private final TeacherContractRepositoryPort teacherContractRepositoryPort;
    private final TeacherContractValidator teacherContractValidator;

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
