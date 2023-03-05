package com.pragma.users.infrastructure.output.services;

import com.pragma.users.domain.model.AuthenticationModel;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UserService {
   private final JwtService jwtService;
    private final IUserRepository objectRepository;
    private final PasswordEncoder encoder;





    public UserEntity register(UserEntity userEntity){

        var user = UserEntity.builder()
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .email(userEntity.getEmail())
                .password(encoder.encode(userEntity.getPassword()))
                .dni(userEntity.getDni())
                .roles(userEntity.getRoles())
                .mobile(userEntity.getMobile())
                .build();
       return objectRepository.save(user);


    }

    public TokenDto registerToken(UserEntity userEntity){
        var jwtToken = jwtService.generateToken(userEntity);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }


    public TokenDto authenticate(UserEntity request) {
//        var user = objectRepository.findByEmail(request.getEmail())
//                .orElseThrow();
        var jwtToken = jwtService.generateToken(request);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }






}



