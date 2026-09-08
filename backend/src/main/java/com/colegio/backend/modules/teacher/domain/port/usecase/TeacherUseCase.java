package com.colegio.backend.modules.teacher.domain.port.usecase;


import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherRequest;

import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherResponse;
import com.colegio.backend.modules.teacher.application.dto.teacher.UpdateTeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherUseCase {

    Page<TeacherResponse> findByAllStatus(String status, String search, Pageable pageable);

    Teacher create(
            TeacherRequest teacherRequest,
            MultipartFile foto ,
            MultipartFile cv);

    Teacher update(
            UpdateTeacherRequest teacherRequest,
            Long id,
            MultipartFile foto,
            MultipartFile cv
    );

    List<TeacherResponse> search(String search);

    Teacher activate(Long id);

    Teacher deactivate(Long id);

}
