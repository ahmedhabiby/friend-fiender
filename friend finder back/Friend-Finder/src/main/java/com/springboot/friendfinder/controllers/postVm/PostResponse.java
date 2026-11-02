package com.springboot.friendfinder.controllers.postVm;

import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostResponse implements Serializable {
    @Schema(example = "100")
    private Long postId;
    @Schema(example = "i am happy")
    private String postContent;
    @Schema(example = "ahmed")
    private String postAuthor;
    @Schema(example = "accept")
    private List<ResponseComment> responseComments;
    @Schema(example = "50")
    private Long numberOfLikes;
    @Schema(example = "20")
    private Long numberOfDislikes;
    @Schema(example = "123.jpg")
    private List<String> mediaUrl;
    @Schema(example = "9-12-2002")
    private LocalDateTime createdAt;
    @Schema(example = "image.jpg")
    private String imageUrl;
}
