package com.pragma.users.application.handler;

import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import org.apache.el.parser.Token;

import java.util.List;

public interface IUserHandler {
    TokenResponseDto saveUser(UserRequestDto userRequestDto, AuthorityName role);
    List<UserResponseDto> getAllUsers();
    boolean emailExists(String email);
    TokenResponseDto userLogin(String email, String password);

    void deleteUser(Long id);

    UserResponseDto getUser(Long userId);

    TokenResponseDto validateToken(String token);


    UserResponseDto saveOwner(UserRequestDto userRequestDto, AuthorityName roleOwner);
}
