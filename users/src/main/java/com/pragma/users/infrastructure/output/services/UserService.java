package com.pragma.users.infrastructure.output.services;

import com.pragma.users.domain.model.AuthenticationModel;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import com.pragma.users.infrastructure.security.JwtService;
import com.pragma.users.infrastructure.security.PreAuthenticatedUserRoleHeaderFilter;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor

public class UserService {
   private final JwtService jwtService;
    private final IUserRepository objectRepository;
    private final PasswordEncoder encoder;



    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

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
        var user = UserEntity.builder()
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .email(userEntity.getEmail())
                .password(encoder.encode(userEntity.getPassword()))
                .dni(userEntity.getDni())
                .roles(AuthorityName.ROLE_ADMIN)
                .mobile(userEntity.getMobile())
                .build();
        var jwtToken = jwtService.generateToken(objectRepository.save(user));
        System.out.println("soy el token");
        System.out.println(jwtToken);

        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }


    public TokenDto authenticate(AuthenticationModel request) {
        var user = objectRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }
    public TokenDto validate(String token) {
       if(jwtService.isTokenExpired(token))
           return null;
       String username = jwtService.extractUsername(token);
        if(!objectRepository.findByEmail(username).isPresent())
            return null;
        return new TokenDto(token);
    }





}

//    public List<RestaurantModel> getRestaurantsByUserId(Long userId) {
//        List<RestaurantModel> restaurants = restTemplate.getForObject("http://localhost:8082/restaurant/" + userId, List.class);
//        return restaurants;
//    }

