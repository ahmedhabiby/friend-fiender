package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PostReactionDto implements Serializable {
    private Long id;
    private post post;
    private users user;
    private int reaction;

}
