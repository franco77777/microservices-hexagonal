package com.pragma.square.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserFeingClient userFeingClient;
    public String getClientPhone(Long userId) {
        return userFeingClient.getUserPhone(userId);
    }
    public void sendMessage(String number,Long orderId){

    }
}
