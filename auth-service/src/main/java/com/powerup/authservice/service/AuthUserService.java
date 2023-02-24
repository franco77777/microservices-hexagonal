package com.powerup.authservice.service;

import com.powerup.authservice.dto.AuthUserDto;
import com.powerup.authservice.dto.TokenDto;
import com.powerup.authservice.entity.AuthUser;
import com.powerup.authservice.repository.AuthUserRepository;
import com.powerup.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUser save(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(dto.getEmail());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .email(dto.getEmail())
                .password(password)
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByEmail(dto.getEmail());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserEmailFromToken(token);
        if(!authUserRepository.findByEmail(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
