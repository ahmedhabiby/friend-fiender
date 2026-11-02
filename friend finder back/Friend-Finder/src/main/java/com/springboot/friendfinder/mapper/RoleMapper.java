package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.rolesDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.model.roles;
import com.springboot.friendfinder.model.users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    roles toEntity(rolesDto rolesDto);
    rolesDto toDto(roles roles);
    List<roles> toListEntity(List<rolesDto> rolesDtos);
    List<rolesDto> toListDto(List<roles> roles);
}
