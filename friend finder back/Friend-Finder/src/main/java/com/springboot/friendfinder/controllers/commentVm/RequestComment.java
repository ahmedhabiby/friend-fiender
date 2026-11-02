package com.springboot.friendfinder.controllers.commentVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class RequestComment implements Serializable {
    @Schema(example = "well good")
    @NotEmpty(message = "content.notempty")
    private String content;
    @Schema(example = "1900")
    private Long postId;
}
