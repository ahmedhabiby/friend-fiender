package com.springboot.friendfinder.jwtAuth;

import com.springboot.friendfinder.dto.usersDto;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUserDetails implements UserDetails {
    private usersDto usersDto;


    public JwtUserDetails(usersDto usersDto) {
        this.usersDto = usersDto;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usersDto.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).toList();
    }

    @Override
    public @Nullable String getPassword() {
        return "{bcrypt}" +usersDto.getPassword();
    }

    @Override
    public String getUsername() {
        return usersDto.getUsername();
    }
}
