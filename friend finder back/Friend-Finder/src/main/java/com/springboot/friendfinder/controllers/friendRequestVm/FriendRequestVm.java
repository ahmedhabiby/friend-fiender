package com.springboot.friendfinder.controllers.friendRequestVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FriendRequestVm implements Serializable {
    @Schema(example = "Ahmed")
@NotEmpty(message = "receiverName.notempty")
    private String receiverName;
}
