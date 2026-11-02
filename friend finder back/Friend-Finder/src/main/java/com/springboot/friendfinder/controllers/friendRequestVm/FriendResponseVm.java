package com.springboot.friendfinder.controllers.friendRequestVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class FriendResponseVm implements Serializable {
    @Schema(example = "100")
    private Long senderId;
    @Schema(example = "100")
    private Long receiverId;
    @Schema(example = "Ahmed")
    private String senderName;
    @Schema(example = "Ahmed")
    private String receiverName;
}
