package com.springboot.friendfinder.filters;

import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.jwtAuth.HandleToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private HandleToken handleToken;

    @Autowired
    AuthFilter(HandleToken handleToken) {
        this.handleToken = handleToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (Objects.nonNull(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);

            usersDto usersDto = handleToken.validateToken(token);
            if (usersDto == null) {

                return;
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            usersDto.getUsername(),
                            null,
                            usersDto.getRoles().stream()
                                    .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                                    .toList()
                    );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();

        return uri.contains("/login")
                || uri.contains("/signup")
                || uri.startsWith("/swagger-ui")
                || uri.startsWith("/v3/api-docs");
    }

}
