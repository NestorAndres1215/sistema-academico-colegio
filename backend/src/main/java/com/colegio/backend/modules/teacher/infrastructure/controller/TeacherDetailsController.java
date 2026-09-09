package com.colegio.backend.modules.teacher.infrastructure.controller;

import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherListResponse;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherDetailsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher-details")
@Tag(name = "Teacher Details")
public class TeacherDetailsController {

    private final TeacherDetailsUseCase teacherDetailsUseCase;

    @GetMapping("/{id}/curriculum/download")
    public ResponseEntity<Resource> downloadCurriculum(@PathVariable Long id) throws MalformedURLException {

        Resource resource = teacherDetailsUseCase.downloadCurriculum(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Operation(summary = "Get teacher details by teacher id")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherListResponse> findByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherDetailsUseCase.findByTeacherIdWithTeacher(teacherId));
    }


}
