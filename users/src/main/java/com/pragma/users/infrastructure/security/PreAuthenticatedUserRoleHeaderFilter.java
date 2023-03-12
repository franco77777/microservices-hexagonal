package com.pragma.users.infrastructure.security;

import com.pragma.users.infrastructure.exception.InfrastructureException;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@RequiredArgsConstructor
public class PreAuthenticatedUserRoleHeaderFilter
        extends GenericFilterBean {

private final IUserRepository userRepository;

private final JwtService jwtService;
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        final String Roles;
        final String UserId;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Roles = jwtService.extractUserRole(jwt);
        UserId = jwtService.extractUserId(jwt);
        Optional<UserEntity> CurrentUser = userRepository.findByEmail(userEmail);
        if(CurrentUser.isEmpty() || !CurrentUser.get().getRoles().toString().equals(Roles) || !CurrentUser.get().getId().toString().equals(UserId)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        jwtService.setToken(jwt);

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(Roles);
        PreAuthenticatedAuthenticationToken authentication
                = new PreAuthenticatedAuthenticationToken(
                userEmail, UserId, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("soy el pre context");
        System.out.println(SecurityContextHolder.getContext());
        chain.doFilter(servletRequest, servletResponse);
    }


}
