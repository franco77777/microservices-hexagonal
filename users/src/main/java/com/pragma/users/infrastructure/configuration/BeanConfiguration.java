package com.pragma.users.infrastructure.configuration;

import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.domain.usecase.UserUseCase;
import com.pragma.users.infrastructure.output.adapter.UserJpaAdapter;
import com.pragma.users.infrastructure.output.mapper.ITokenDtoMapper;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository objectRepository;
    private final IUserEntityMapper objectEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ITokenDtoMapper tokenDtoMapper;



    @Bean
    public IUserPersistencePort objectPersistencePort(){
        return new UserJpaAdapter(objectRepository,objectEntityMapper,passwordEncoder,jwtService,tokenDtoMapper);
    };
    @Bean
    public IUserServicePort objectServicePort(){
        return new UserUseCase(objectPersistencePort());    }



}
