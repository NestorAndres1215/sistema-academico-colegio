package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTeacherContractRepository extends JpaRepository <TeacherContractEntity,Long>{
    Optional<TeacherContractEntity> findByTeacher_Id(Long id);
}
