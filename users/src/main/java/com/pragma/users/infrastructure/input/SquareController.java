package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/square")
@RequiredArgsConstructor
public class SquareController {
    private final IUserRepository userRepository;

    private final IUserHandler userHandler;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserPhone(@PathVariable Long userId) {
        UserResponseDto userResponse = userHandler.getUser(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/role/{userId}")
    public ResponseEntity<String> getRole(@PathVariable Long userId) {
            String result;
            UserResponseDto user = userHandler.getUser(userId);
            if(user.getRoles().toString().contains("ROLE_OWNER")) {
                result = "true";
            }
            else{
                result = "false";
            }
        return ResponseEntity.ok(result);
    }
}
