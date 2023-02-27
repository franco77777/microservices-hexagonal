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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    private final UserService service;

    @Bean
    public IObjectPersistencePort objectPersistencePort(){
        return new ObjectJpaAdapter(objectRepository,objectEntityMapper,service);
    };
    @Bean
    public IObjectServicePort objectServicePort(){
        return new ObjectUseCase(objectPersistencePort());    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> objectRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }




}
