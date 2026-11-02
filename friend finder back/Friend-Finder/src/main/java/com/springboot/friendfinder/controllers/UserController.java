package com.springboot.friendfinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

     @GetMapping("/all/user/registerd")
    public ResponseEntity<Long> getMethodName() {
        return ResponseEntity.ok(userService.countAllUsers());
    }
}
