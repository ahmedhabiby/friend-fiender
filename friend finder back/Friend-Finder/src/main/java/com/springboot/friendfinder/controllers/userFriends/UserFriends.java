package com.springboot.friendfinder.controllers.userFriends;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserFriends implements Serializable {
    @Schema(example = "/image/1.jpg")
    private String imageUrl;
    @Schema(example = "ahmed")
    private String userName;
    @Schema(example = "engineer")
    private String jop;
}
