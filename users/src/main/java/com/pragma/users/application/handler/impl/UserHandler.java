package com.pragma.users.application.handler.impl;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.mapper.IUserRequestMapper;
import com.pragma.users.application.mapper.IUserResponseMapper;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.api.IUserServicePort;
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
    private final IUserServicePort objectServicePort;
    private final IUserRequestMapper objectRequestMapper;
    private final IUserResponseMapper objectResponseMapper;
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto, AuthorityName role) {
        UserModel userModel =  objectRequestMapper.toUserModel(userRequestDto);

       return objectResponseMapper.toUserResponseDto(objectServicePort.saveUser(userModel,role));


    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return  objectResponseMapper.toUserResponseList(objectServicePort.getAllUsers());
    }

    @Override
    public boolean emailExists(String email) {
        return objectServicePort.emailExists(email);
    }

    @Override
    public UserResponseDto userExists(String email, String password) {
        return objectResponseMapper.toUserResponseDto(objectServicePort.userExists(email,password));
    }

    @Override
    public void deleteUser(Long id) {
         objectServicePort.deleteUser(id);
    }

}
