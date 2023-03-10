package com.pragma.users.infrastructure.input;

import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
