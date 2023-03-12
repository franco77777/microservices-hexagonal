package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.request.AuthenticateRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserAuthController {
    private final IUserHandler userHandler;
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserEntityMapper userEntityMapper;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticate(
            @RequestBody @Validated AuthenticateRequestDto request
    ) {
        UserResponseDto user = userHandler.userExists(request.getEmail(), request.getPassword());
        UserEntity result  = userEntityMapper.responseToEntity(user);
        return ResponseEntity.ok(jwtService.authenticate(result));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateUser(@RequestParam String token){
        TokenDto tokenDto = jwtService.validate(token);
        return ResponseEntity.ok(tokenDto);
    }



}
