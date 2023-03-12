package com.pragma.square.infrastructure.utils;

import com.pragma.square.infrastructure.security.JwtService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserFeingClientConfig {
    private final JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + jwtService.getToken());
        };
    }
}
