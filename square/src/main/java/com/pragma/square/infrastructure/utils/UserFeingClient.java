package com.pragma.square.infrastructure.utils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//, url = "http://localhost:8080"
@FeignClient(name = "users-service")
public interface UserFeingClient {
    @GetMapping("/user/square/{userId}")
    String getUserPhone(@PathVariable Long userId);

    @GetMapping("user/square/role/{userId}")
    String getRole(@PathVariable Long userId);
}
