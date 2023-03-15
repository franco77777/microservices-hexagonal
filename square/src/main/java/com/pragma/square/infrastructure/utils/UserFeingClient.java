package com.pragma.square.infrastructure.utils;
import com.pragma.square.domain.models.ClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//, url = "http://localhost:8080"
@FeignClient(name = "users-service", configuration = UserFeingClientConfig.class)
public interface UserFeingClient {
    @GetMapping("/user/square/{userId}")
    ClientModel getClient(@PathVariable Long userId);

    @GetMapping("user/square/role/{userId}")
    String getRole(@PathVariable Long userId);
}
