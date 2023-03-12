package com.pragma.square.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserFeingClient userFeingClient;
    public String getClientPhone(Long userId) {
        return userFeingClient.getUserPhone(userId);
    }

    public String getClientRole(Long userId) {
        return userFeingClient.getRole(userId);
    }






}
