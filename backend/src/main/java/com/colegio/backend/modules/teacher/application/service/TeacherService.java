package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.dto.teacher.CreateTeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherResponse;
import com.colegio.backend.modules.teacher.application.dto.teacher.UpdateTeacherRequest;
import com.colegio.backend.modules.teacher.application.mapper.TeacherContractMapper;
import com.colegio.backend.modules.teacher.application.mapper.TeacherDetailsMapper;
import com.colegio.backend.modules.teacher.application.mapper.TeacherMapper;
import com.colegio.backend.modules.teacher.application.validator.TeacherValidator;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherDetailsUseCase;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherUseCase;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.usecase.UserUseCase;
import com.colegio.backend.shared.constant.RoleConstants;
import com.colegio.backend.shared.constant.StatusConstants;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherService implements TeacherUseCase {

    private final TeacherMapper teacherMapper;
    private final TeacherDetailsMapper teacherDetailsMapper;
    private final TeacherContractMapper teacherContractMapper;
    private final TeacherRepositoryPort teacherRepositoryPort;
    private final UserUseCase userUseCase;
    private final TeacherDetailsUseCase teacherDetailsUseCase;
    private final TeacherContractUseCase teacherContractUseCase;
    private final FileUseCase fileUseCase;
    private final TeacherValidator teacherValidator;

    @Override
    public Page<TeacherResponse> findByAllStatus(String status, String search, Pageable pageable) {

        return teacherRepositoryPort.findByAllStatus(status, search, pageable)
                .map(teacherMapper::toResponse);
    }

    @Override
    public Teacher create(TeacherRequest teacherRequest, MultipartFile foto, MultipartFile cv) {

        teacherValidator.validateBirthDate(
                teacherRequest.birthDate(),
                teacherRequest.startDate(),
                teacherRequest.endDate()
        );

        CreateTeacherRequest createTeacherRequest = teacherMapper.toCreateRequest(teacherRequest);

        Teacher teacher = createTeacher(createTeacherRequest, teacherRequest.email(), foto);

        TeacherDetails teacherDetails = teacherDetailsMapper.toDomain(teacherRequest,teacher);

        teacherDetailsUseCase.create(teacherDetails,teacher, cv);

        TeacherContract teacherContract = teacherContractMapper.toDomain(teacherRequest,teacher);

        teacherContractUseCase.create(teacherContract,teacher);

        return teacher;
    }

    @Override
    public Teacher update(
            UpdateTeacherRequest teacherRequest,
            Long id,
            MultipartFile foto,
            MultipartFile cv
    ) {

        Teacher teacher = findTeacherById(id);

        teacherValidator.validateUpdate(teacherRequest, teacher);

        teacherMapper.updateDomain(teacherRequest, teacher);

        updatePhoto(teacher, foto);

        Teacher teacherModel =teacherRepositoryPort.save(teacher);

        teacherDetailsUseCase.update(teacherRequest,teacherModel,cv);

        return teacherModel;
    }

    @Override
    public List<TeacherResponse> search(String search) {
        List<Teacher> teachers;

        if (search == null || search.isBlank()) {
            teachers = teacherRepositoryPort.findRandom(5);
        } else {
            teachers = teacherRepositoryPort.search(search.trim(), 5);
        }

        return teachers.stream()
                .map(teacherMapper::toResponse)
                .toList();
    }


    private  Teacher createTeacher(
            CreateTeacherRequest createTeacherRequest,
            String email,
            MultipartFile foto
    ){

        String code = generateCode(createTeacherRequest.dni());

        User user = userUseCase.save(email,code, createTeacherRequest.dni(), RoleConstants.TEACHER);

        teacherValidator.validate(createTeacherRequest,code);

        Teacher teacher = teacherMapper.toDomain(createTeacherRequest,user,code);

        savePhoto(teacher, foto);

        return teacherRepositoryPort.save(teacher);
    }

    private void savePhoto(Teacher teacher, MultipartFile foto) {

        if (foto == null || foto.isEmpty()) {
            return;
        }

        String fileUrl = fileUseCase.storeFile(foto, "teacher");

        teacher.setPhoto(fileUrl);
    }

    private void updatePhoto(Teacher teacher, MultipartFile foto) {

        if (foto == null || foto.isEmpty()) {
            return;
        }

        String oldPhotoUrl = teacher.getPhoto();

        String fileUrl = fileUseCase.storeFile(foto, "teacher");

        teacher.setPhoto(fileUrl);

        if (oldPhotoUrl != null && !oldPhotoUrl.isBlank()) {
            fileUseCase.deleteFile(oldPhotoUrl);
        }
    }

    private String generateCode(String dni) {
        return "PR-" + dni;
    }


    @Override
    public Teacher activate(Long id) {
        return updateStatus(id, StatusConstants.ACTIVE);
    }

    @Override
    public Teacher deactivate(Long id) {
        return updateStatus(id, StatusConstants.INACTIVE);
    }

    private Teacher updateStatus(Long id, String status) {

        Teacher teacher = findTeacherById(id);

        teacher.setStatus(status);

        return teacherRepositoryPort.save(teacher);
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }
}
