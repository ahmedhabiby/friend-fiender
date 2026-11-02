package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.model.UserActivity;
import com.springboot.friendfinder.repo.UserActivityRepo;
import com.springboot.friendfinder.service.UserActivityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    private UserActivityRepo userActivityRepo;

    @Autowired
    public UserActivityServiceImpl(UserActivityRepo userActivityRepo) {
        this.userActivityRepo = userActivityRepo;
    }

    @Override
    @Async
    public void saveActivity(String username, String action, String endpoint, HttpServletRequest request) {
        UserActivity ua = new UserActivity();
        ua.setUsername(username);
        ua.setAction(action);
        ua.setEndpoint(endpoint);
        ua.setIpAddress(request.getRemoteAddr());
        ua.setUserAgent(request.getHeader("User-Agent"));

        userActivityRepo.save(ua);
    }
}
