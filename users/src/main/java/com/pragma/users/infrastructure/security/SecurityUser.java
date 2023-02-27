//package com.pragma.users.infrastructure.security;
//
//import com.pragma.users.infrastructure.output.entity.UserEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//public class SecurityUser implements UserDetails {
//
//    private final UserEntity user;
//
//    PasswordEncoder passwordEncoder;
//
//    public SecurityUser(UserEntity user) {
//        this.user = user;
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(user
//                        .getRoles().toString()
//                        .split(","))
//                         .map(SimpleGrantedAuthority::new)
//                         .toList();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
