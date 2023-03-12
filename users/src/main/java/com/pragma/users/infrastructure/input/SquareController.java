package com.pragma.users.infrastructure.input;

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

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserPhone(@PathVariable Long userId) {
        String phone = userRepository.findById(userId).get().getMobile();
            return ResponseEntity.ok(phone);
    }

    @GetMapping("/role/{userId}")
    public ResponseEntity<String> getRole(@PathVariable Long userId) {
            String result;
            Optional<UserEntity> user = userRepository.findById(userId);
            if(user.get().getRoles().toString().contains("ROLE_OWNER")) {
                result = "true";
            }
            else{
                result = "false";
            }
        return ResponseEntity.ok(result);

    }

}
