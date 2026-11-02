package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.PostReaction;
import com.springboot.friendfinder.model.comments;
import com.springboot.friendfinder.model.media;
import com.springboot.friendfinder.model.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class postDto implements Serializable {
    @Schema(example = "100")
    private Long id;
    @Schema(example = "i am happy")
    private String content ;
    private users users;
    private List<media> media;
    private List<PostReaction>  reactions;
    private List<comments> comments;

}
