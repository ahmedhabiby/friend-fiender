package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.PostReactionDto;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.model.PostReaction;
import com.springboot.friendfinder.model.post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostReactionMapper {
    PostReaction toEntity(PostReactionDto postReactionDto);
    PostReactionDto toDto(PostReaction postReaction);
    List<PostReaction> toListEntity(List<PostReactionDto> postReactionDtos);
    List<PostReactionDto>toListDto(List<PostReaction> postReactions);

}
