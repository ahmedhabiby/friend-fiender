package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.enums.mediaEnum;
import com.springboot.friendfinder.model.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaDto {
    private Long id;
    private String mediaUrl;
    private mediaEnum mediaType;
    private post post;

}
