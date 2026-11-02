package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.CommentDto;
import com.springboot.friendfinder.dto.FriendRequestDto;
import com.springboot.friendfinder.model.FriendRequest;
import com.springboot.friendfinder.model.comments;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendRequestMapper {
    FriendRequest toEntity(FriendRequestDto friendRequestDto);
    FriendRequestDto toDto(FriendRequest friendRequest);
    List<FriendRequest> toListEntity(List<FriendRequestDto> friendRequestDtos);
    List<FriendRequestDto>toListDto(List<FriendRequest> friendRequests);
}
