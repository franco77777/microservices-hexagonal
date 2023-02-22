package com.pragma.users.infrastructure.output.utils;

import com.pragma.users.infrastructure.output.entity.UserEntity;

import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final IUserRepository userRepository;

    private final PasswordEncoder encoder;




    @Override
    public void run(String... args) throws Exception {
        var user = UserEntity.builder()
                .dni("123456789")
                .roles(AuthorityName.ROLE_ADMIN)
                .surname("Surname")
                .username("admin")
                .email("kenaa@example.com")
                .mobile("123456789")
                .password(encoder.encode("password"))
                .build();
          this.userRepository.save(user);
        }}
