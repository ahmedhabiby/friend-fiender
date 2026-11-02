package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.users;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class rolesDto {
    private String roleName;
    private List<users> users;
}
