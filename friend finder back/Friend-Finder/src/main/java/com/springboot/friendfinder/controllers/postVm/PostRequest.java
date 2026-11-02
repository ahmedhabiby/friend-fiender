package com.springboot.friendfinder.controllers.postVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PostRequest implements Serializable {
    @Schema(example = "do well")
    @NotEmpty(message = "content.notempty")
    private String content;
    @Schema(example = "nnn.jpg")
    private String mediaUrl;
    @Schema(example = "image")
    private String mediaType;
}
