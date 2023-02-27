package com.pragma.users.application.mapper;

import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", //decimos que spring lo tome como un bean y se puede injectar la dependencia
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // ignora errores si no mapea algo
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectResponseMapper {
    UserResponseDto toUserResponseDto(UserModel userModel);
    List<UserResponseDto> toUserResponseList(List<UserModel> userModelList);
}
