package com.pragma.users.infrastructure.output.services;

import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final IUserRepository objectRepository;
    private final PasswordEncoder encoder;
    //private final RestTemplate restTemplate;

    public UserEntity register(UserEntity userEntity){

        var user = UserEntity.builder()
                .username(userEntity.getUsername())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .password(encoder.encode(userEntity.getPassword()))
                .dni(userEntity.getDni())
                .roles(userEntity.getRoles())
                .mobile(userEntity.getMobile())
                .build();
       objectRepository.save(user);
       return user;
    }

//    public List<RestaurantModel> getRestaurantsByUserId(Long userId) {
//        List<RestaurantModel> restaurants = restTemplate.getForObject("http://localhost:8082/restaurant/" + userId, List.class);
//        return restaurants;
//    }
}
