package com.pragma.square.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

public class PreAuthenticatedUserRoleHeaderFilter
        extends GenericFilterBean {

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String rolesString = "ROLE_ADMIN";
        String userName = "franco";// extract the username
        List < GrantedAuthority > authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(rolesString);
        PreAuthenticatedAuthenticationToken authentication
                = new PreAuthenticatedAuthenticationToken(
                userName, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("soy el pre context");
        System.out.println(SecurityContextHolder.getContext());
        chain.doFilter(servletRequest, servletResponse);
    }
}