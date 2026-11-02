package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.dto.ProfileDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.ProfileMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.profile;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.repo.ProfileRepo;
import com.springboot.friendfinder.service.ProfileService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private UserService userService;
    private UserMapper userMapper;
    private ProfileRepo profileRepo;
    private ProfileMapper profileMapper;

    @Autowired
    public void setUserService(UserService userService, UserMapper userMapper, ProfileRepo profileRepo, ProfileMapper profileMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.profileRepo = profileRepo;
        this.profileMapper = profileMapper;
    }
    @Override
    @CacheEvict(value = "profile", allEntries = true)
    public ProfileDto saveProfile(ProfileDto profileDto) {
        if(getProfileForLoginUser()!=null){
            throw new RuntimeException("profile.exists");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto user= userService.findUserByUsername(username);
        users users=userMapper.toEntity(user);
        profile profile = profileMapper.toEntity(profileDto);
        profile.setUser(users);
        profileRepo.save(profile);
        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileDto getProfileForLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto user= userService.findUserByUsername(username);
        profile profile = profileRepo.findprofileByUserId(user.getId());
        return profileMapper.toDto(profile);
    }
}
