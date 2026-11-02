package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.loginVm.RequestVm;
import com.springboot.friendfinder.controllers.loginVm.ResponseVm;
import com.springboot.friendfinder.controllers.signupVm.SignupRequest;
import com.springboot.friendfinder.controllers.signupVm.SignupResponse;

public interface AuthService {
    ResponseVm login(RequestVm requestVm);
    SignupResponse signup( SignupRequest signupRequest);
}
