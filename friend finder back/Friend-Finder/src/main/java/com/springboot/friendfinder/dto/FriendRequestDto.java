package com.springboot.friendfinder.dto;


import com.springboot.friendfinder.model.users;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FriendRequestDto {
    private Long id;
    private users sender;
    private users receiver;
}
