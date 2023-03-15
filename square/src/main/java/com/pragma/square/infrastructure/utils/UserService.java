package com.pragma.square.infrastructure.utils;

import com.pragma.square.domain.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserFeingClient userFeingClient;
    public ClientModel getClient(Long userId) {
        return userFeingClient.getClient(userId);
    }

    public String getClientRole(Long userId) {
        return userFeingClient.getRole(userId);
    }






}
