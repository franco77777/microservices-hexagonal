package com.pragma.users.application.handler;

import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;
import java.util.Optional;

public interface IObjectHandler {
    UserResponseDto saveObject(UserRequestDto userRequestDto, AuthorityName role);
    List<UserResponseDto> getAllObjects();
    boolean emailExists(String email);

    UserResponseDto userExists(String email, String password);


}
