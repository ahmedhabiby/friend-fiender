package com.springboot.friendfinder.service;


import com.springboot.friendfinder.dto.rolesDto;

public interface RoleService {
    rolesDto findByRoleName(String roleName);
}
