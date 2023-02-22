package com.pragma.users.domain.api;

import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface IObjectServicePort {
    UserModel saveObject(UserModel userModel, AuthorityName role);
    List<UserModel> getAllObjects();

    UserModel emailExists(String email);
}

