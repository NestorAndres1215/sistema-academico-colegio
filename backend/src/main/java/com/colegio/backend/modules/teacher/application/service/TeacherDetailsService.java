package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.dto.teacher.UpdateTeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherDetailsRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherDetailsUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class TeacherDetailsService implements TeacherDetailsUseCase {

    private final TeacherDetailsRepositoryPort teacherDetailsRepositoryPort;
    private final FileUseCase fileUseCase;

    @Override
    public TeacherDetails create(
            TeacherDetails teacherDetails,
            Teacher teacher,
            MultipartFile cv
    ) {

        teacherDetails.setTeacher(teacher);

        saveCv(teacherDetails, cv);

        return teacherDetailsRepositoryPort.save(teacherDetails);
    }

    @Override
    public TeacherDetails update(
            UpdateTeacherRequest updateTeacherRequest,
            Teacher teacher,
            MultipartFile cv
    ) {

        TeacherDetails teacherDetails = findByTeacherId(teacher.getId());

        updateCv(teacherDetails, cv);

        return teacherDetailsRepositoryPort.save(teacherDetails);
    }
    @Override
    public Resource downloadCurriculum(Long teacherId) throws MalformedURLException {
        TeacherDetails details = findByTeacherId(teacherId);

        if (details.getCurriculum() == null || details.getCurriculum().isBlank()) {
            throw new NotFoundException("El profesor no tiene curriculum registrado");
        }

        return fileUseCase.loadAsResource(details.getCurriculum());
    }


    private TeacherDetails findByTeacherId(Long teacherId) {
        return teacherDetailsRepositoryPort.findByTeacher_Id(teacherId)
                .orElseThrow(() ->
                        new NotFoundException("Detalle del profesor no encontrado"));
    }

    private void saveCv(TeacherDetails teacherDetails, MultipartFile cv) {

        if (cv == null || cv.isEmpty()) {
            return;
        }

        String fileUrl = fileUseCase.storeFile(cv, "teacher-cv");

        teacherDetails.setCurriculum(fileUrl);
    }

    private void updateCv(TeacherDetails teacherDetails, MultipartFile cv) {

        if (cv == null || cv.isEmpty()) {
            return;
        }

        String oldCvUrl = teacherDetails.getCurriculum();

        String fileUrl = fileUseCase.storeFile(cv, "teacher-cv");

        teacherDetails.setCurriculum(fileUrl);

        if (oldCvUrl != null && !oldCvUrl.isBlank()) {
            fileUseCase.deleteFile(oldCvUrl);
        }
    }


}
