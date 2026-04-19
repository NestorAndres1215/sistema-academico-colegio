package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministratorMapper {

    private final  UserMapper userMapper;

    public Administrator toDomain(AdministratorEntity entity) {
        if (entity == null) return null;


        return Administrator.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .age(entity.getAge())
                .dni(entity.getDni())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .profile(entity.getProfile())
                .gender(entity.getGender())
                .nationality(entity.getNationality())
                .status(entity.getStatus())
                .user(userMapper.toDomain(entity.getUserEntity()))
                .build();
    }

    public AdministratorEntity toEntity(Administrator administrator) {
        if (administrator == null) return null;

        return AdministratorEntity.builder()
                .id(administrator.getId())
                .firstName(administrator.getFirstName())
                .middleName(administrator.getMiddleName())
                .paternalLastName(administrator.getPaternalLastName())
                .maternalLastName(administrator.getMaternalLastName())
                .age(administrator.getAge())
                .dni(administrator.getDni())
                .phone(administrator.getPhone())
                .birthDate(administrator.getBirthDate())
                .profile(administrator.getProfile())
                .gender(administrator.getGender())
                .nationality(administrator.getNationality())
                .status(administrator.getStatus())
                .userEntity(userMapper.toEntity(administrator.getUser()))
                .build();
    }
}
