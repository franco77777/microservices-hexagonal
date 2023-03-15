package com.pragma.users.application.mapper;

import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", //decimos que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignora errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)


public interface IUserRequestMapper {
    UserModel toUserModel(UserRequestDto userRequestDto);
    UserEntity toUserEntity(UserResponseDto userRequestDto);

    AuthorityNameModel convertToModel(AuthorityName role);
}