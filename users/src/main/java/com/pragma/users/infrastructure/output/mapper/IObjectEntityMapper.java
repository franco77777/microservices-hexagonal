package com.pragma.users.infrastructure.output.mapper;

import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", //decimos que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignora errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectEntityMapper {
    UserEntity toEntity(UserModel user);
    UserModel toUserModel(UserEntity userEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntityList);

    UserEntity responseToEntity(UserResponseDto user);
}
