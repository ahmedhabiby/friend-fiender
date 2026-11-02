package com.springboot.friendfinder.controllers.friendRequestVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfUserFollower implements Serializable {
    @Schema(example = "ahmed")
    private String userName;
    @Schema(example = "100")
    private long numberOfFollowers;
}
