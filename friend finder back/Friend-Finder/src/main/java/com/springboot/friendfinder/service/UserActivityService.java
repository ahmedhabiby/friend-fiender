package com.springboot.friendfinder.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserActivityService {
    void saveActivity(String username,
                      String action,
                      String endpoint,
                      HttpServletRequest request);

}
