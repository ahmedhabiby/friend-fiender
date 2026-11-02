package com.springboot.friendfinder.service;

import com.springboot.friendfinder.dto.ProfileDto;

public interface ProfileService {
    ProfileDto saveProfile(ProfileDto profileDto);
    ProfileDto getProfileForLoginUser();
}
