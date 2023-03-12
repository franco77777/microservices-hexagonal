package com.pragma.users.application.handler;

import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface IUserHandler {
    UserResponseDto saveUser(UserRequestDto userRequestDto, AuthorityName role);
    List<UserResponseDto> getAllUsers();
    boolean emailExists(String email);
    UserResponseDto userExists(String email, String password);

    void deleteUser(Long id);
}
