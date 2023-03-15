package com.pragma.users.application.handler.impl;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.mapper.ITokenResponseMapper;
import com.pragma.users.application.mapper.IUserRequestMapper;
import com.pragma.users.application.mapper.IUserResponseMapper;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final ITokenResponseMapper tokenResponseMapper;

    @Override
    public TokenResponseDto saveUser(UserRequestDto userRequestDto, AuthorityName role) {
        AuthorityNameModel authorityNameModel = userRequestMapper.convertToModel(role);
        UserModel userModel =  userRequestMapper.toUserModel(userRequestDto);
        return tokenResponseMapper.toTokenResponseDto(userServicePort.saveUser(userModel,authorityNameModel));


    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return  userResponseMapper.toUserResponseList(userServicePort.getAllUsers());
    }

    @Override
    public boolean emailExists(String email) {
        return userServicePort.emailExists(email);
    }

    @Override
    public TokenResponseDto userLogin(String email, String password) {
        return tokenResponseMapper.toTokenResponseDto(userServicePort.userLogin(email,password));
    }

    @Override
    public void deleteUser(Long id) {
         userServicePort.deleteUser(id);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        return userResponseMapper.toUserResponseDto(userServicePort.getUser(userId));
    }

    @Override
    public TokenResponseDto validateToken(String token) {
        return tokenResponseMapper.toTokenResponseDto(userServicePort.validateToken(token));
    }

    @Override
    public UserResponseDto saveOwner(UserRequestDto userRequestDto, AuthorityName roleOwner) {
        AuthorityNameModel authorityNameModel = userRequestMapper.convertToModel(roleOwner);
        UserModel userModel =  userRequestMapper.toUserModel(userRequestDto);
        return userResponseMapper.toUserResponseDto(userServicePort.saveOwner(userModel,authorityNameModel));
    }

}
