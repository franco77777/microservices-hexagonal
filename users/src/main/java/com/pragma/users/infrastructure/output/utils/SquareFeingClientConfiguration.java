package com.pragma.users.infrastructure.output.utils;

import com.pragma.users.infrastructure.security.JwtService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SquareFeingClientConfiguration {
    private final JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + jwtService.getToken());
        };
    }
}
