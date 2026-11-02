package com.springboot.friendfinder.controllers.reactionVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ReactionRequest implements Serializable {
    @Schema(example = "100")
    private Long postId;
    @Schema(example = "100")
    private int reaction;
}
