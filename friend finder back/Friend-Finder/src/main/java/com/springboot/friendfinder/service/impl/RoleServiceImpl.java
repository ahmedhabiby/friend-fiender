package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.repo.RoleRepo;
import com.springboot.friendfinder.dto.rolesDto;
import com.springboot.friendfinder.mapper.RoleMapper;
import com.springboot.friendfinder.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;
    private RoleMapper roleMapper;
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo, RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }
    @Override
    public rolesDto findByRoleName(String roleName) {
        return roleMapper.toDto(roleRepo.findRolesByName(roleName));
    }
}
