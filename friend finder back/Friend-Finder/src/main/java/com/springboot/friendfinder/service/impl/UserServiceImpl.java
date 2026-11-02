package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.repo.RoleRepo;
import com.springboot.friendfinder.repo.UserRepo;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.roles;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private UserMapper usermapper;
    private RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper usermapper, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.usermapper = usermapper;
    }
    @Override
    public usersDto findUserByUsername(String username) {
        users user = userRepo.findUserByUsername(username);
        usersDto usersDto = usermapper.toDto(user);
        return usersDto;
    }

    @Override
    public usersDto saveUser(usersDto usersDto) {
        users user = usermapper.toEntity(usersDto);
        roles role = roleRepo.findRolesByName("USER");
        user.setRoles(List.of(role));
        return usermapper.toDto(userRepo.save(user));
    }
    @Override
    public Long countAllUsers() {
        return userRepo.count();
    }
    }

