package com.springboot.friendfinder.jwtAuth;

import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserdetailService implements UserDetailsService {
    private UserService userService;

    public JwtUserdetailService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           usersDto usersDto = userService.findUserByUsername(username);
           if (usersDto==null){
               throw new UsernameNotFoundException("user not found");
           }
           return new JwtUserDetails(usersDto);
    }
}
