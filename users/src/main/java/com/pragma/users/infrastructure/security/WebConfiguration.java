package com.pragma.users.infrastructure.security;


import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebConfiguration {
   // private final JwtAuthenticationFilter jwtAuthFilter;
   // private final UserDetailsServiceImpl userDetailsService;

    //private final IUserRepository userRepository;


//    @Bean
//    //authentication
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.withUsername("franco")
//                .password(encoder.encode("1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("franco2")
//                .password("2")
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//        //return new UserInfoUserDetailsService();
//    }
    private final IUserRepository userRepository;

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        PreAuthenticatedUserRoleHeaderFilter authFilter
                = new PreAuthenticatedUserRoleHeaderFilter(userRepository,jwtService);
        return http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/user/validate").permitAll()
                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/user/register/client").permitAll()
                .requestMatchers("/user/register/admin").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
