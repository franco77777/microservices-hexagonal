package com.pragma.users.infrastructure.security;

import com.pragma.users.infrastructure.output.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    final IUserRepository objectRepository;

    public UserDetailsServiceImpl(IUserRepository objectRepository) {
        this.objectRepository = objectRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return objectRepository
                .findByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
    }
}
