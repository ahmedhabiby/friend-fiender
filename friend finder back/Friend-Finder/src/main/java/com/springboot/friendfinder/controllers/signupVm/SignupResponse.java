package com.springboot.friendfinder.controllers.signupVm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponse implements Serializable {
    private String token;
}
