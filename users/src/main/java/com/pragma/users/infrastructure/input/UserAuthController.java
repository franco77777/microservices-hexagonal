package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.request.AuthenticateRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserAuthController {
    private final IUserHandler userHandler;
    private final JwtService jwtService;
    private final IUserEntityMapper userEntityMapper;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestBody @Validated AuthenticateRequestDto request
    ) {
        TokenResponseDto token = userHandler.userLogin(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenResponseDto> validateUser(@RequestParam String token){
        TokenResponseDto tokenResponse = userHandler.validateToken(token);
        return ResponseEntity.ok(tokenResponse);
    }



}
