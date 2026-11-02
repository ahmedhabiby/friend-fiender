package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.model.users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    users toEntity(usersDto usersDto);
    usersDto toDto(users users);
    List<users> toListEntity(List<usersDto> usersDto);
    List<usersDto> toListDto(List<users> users);
}
