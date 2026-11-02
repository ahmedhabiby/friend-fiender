package com.springboot.friendfinder.controllers.friendRequestVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class FriendResponseForLoginUser implements Serializable {
    @Schema(example = "ahmed")
    private String senderNames;
    @Schema(example = "19/2/2026")
    private LocalDateTime createdAts;
    @Schema(example = "100")
    private Long senderId;
    @Schema(example = "100")
    private Long receiverId;
}
