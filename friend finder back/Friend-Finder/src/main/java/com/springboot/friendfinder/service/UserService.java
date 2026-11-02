package com.springboot.friendfinder.service;

import java.util.List;

import com.springboot.friendfinder.dto.usersDto;

public interface UserService {
    usersDto findUserByUsername(String username);
    usersDto saveUser(usersDto usersDto);
    Long countAllUsers();
}
