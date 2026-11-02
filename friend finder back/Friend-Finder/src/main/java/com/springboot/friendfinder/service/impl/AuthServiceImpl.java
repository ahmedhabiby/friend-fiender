package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.loginVm.RequestVm;
import com.springboot.friendfinder.controllers.loginVm.ResponseVm;

import com.springboot.friendfinder.controllers.signupVm.SignupRequest;
import com.springboot.friendfinder.controllers.signupVm.SignupResponse;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.jwtAuth.HandleToken;
import com.springboot.friendfinder.repo.UserRepo;
import com.springboot.friendfinder.service.AuthService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class AuthServiceImpl implements AuthService {
private UserService userService;
private HandleToken handleToken;
private PasswordEncoder passwordEncoder;
private UserRepo userRepo;
@Autowired
AuthServiceImpl(UserService userService, HandleToken handleToken, PasswordEncoder passwordEncoder, UserRepo userRepo) {
    this.userService = userService;
    this.passwordEncoder= passwordEncoder;
    this.handleToken = handleToken;
    this.userRepo = userRepo;
}
    @Override
    public ResponseVm login(RequestVm requestVm) {

        usersDto user = userService.findUserByUsername(requestVm.getUsername());
        if(user==null){
            throw new RuntimeException("username.mis");
        }
        if (!(passwordEncoder.matches(requestVm.getPassword(), user.getPassword().replace("{bcrypt}","")))) {
            throw new RuntimeException("password.mis");
        }
        return new ResponseVm(handleToken.createToken(user));
    }

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        usersDto userdto = userService.findUserByUsername(signupRequest.getUsername());
        if(userdto!=null){        
        if(userdto.getUsername().equals(signupRequest.getUsername())){
            throw new RuntimeException("username.unique");
        }
    }
       usersDto usersDto=new usersDto();
       usersDto.setUsername(signupRequest.getUsername());
       usersDto.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
       usersDto.setEmail(signupRequest.getEmail());
       usersDto user=userService.saveUser(usersDto);
       return new SignupResponse(handleToken.createToken(user));
    }
}
