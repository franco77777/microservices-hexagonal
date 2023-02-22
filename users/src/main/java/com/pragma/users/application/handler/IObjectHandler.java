package com.pragma.users.application.handler;

import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface IObjectHandler {
    UserResponseDto saveObject(UserRequestDto userRequestDto, AuthorityName role);
    List<UserResponseDto> getAllObjects();
    UserResponseDto emailExists(String email);


}
