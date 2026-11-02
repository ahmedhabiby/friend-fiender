package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.users;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private Long id;

    private String content;

    private post post;

    private users user;
}
