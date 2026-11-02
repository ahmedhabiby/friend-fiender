package com.springboot.friendfinder.controllers.commentVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ResponseComment implements Serializable {
    @Schema(example = "100")
    private Long comment_id;
    @Schema(example = "good luck")
    private String comment_content;
    @Schema(example = "100")
    private Long comment_author_id;
    @Schema(example = "ahmed")
    private String comment_author_name;
    @Schema(example = "100")
    private Long post_id;
    @Schema(example = "19/2/2026")
    private LocalDateTime comment_created_at;
    @Schema(example = "image.jpg")
    private String comment_author_image_url;
}
