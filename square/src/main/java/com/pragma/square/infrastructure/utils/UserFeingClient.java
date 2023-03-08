package com.pragma.square.infrastructure.utils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", url = "http://localhost:8080")
public interface UserFeingClient {
    @GetMapping("/user/square/{userId}")
    String getUserPhone(@PathVariable Long userId);
}
