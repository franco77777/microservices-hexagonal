package com.pragma.users.infrastructure.configuration;

import com.pragma.users.domain.api.IObjectServicePort;
import com.pragma.users.domain.spi.IObjectPersistencePort;
import com.pragma.users.domain.usecase.ObjectUseCase;
import com.pragma.users.infrastructure.output.adapter.ObjectJpaAdapter;
import com.pragma.users.infrastructure.output.mapper.IObjectEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.output.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;
   private final PasswordEncoder passwordEncoder;
    private final UserService service;

    @Bean
    public IObjectPersistencePort objectPersistencePort(){
        return new ObjectJpaAdapter(objectRepository,objectEntityMapper,service);
    };
    @Bean
    public IObjectServicePort objectServicePort(){
        return new ObjectUseCase(objectPersistencePort(),passwordEncoder);    }



}
