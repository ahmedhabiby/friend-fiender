package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.CommentDto;
import com.springboot.friendfinder.dto.MediaDto;
import com.springboot.friendfinder.model.comments;
import com.springboot.friendfinder.model.media;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    comments toEntity(CommentDto commentDto);
    CommentDto toDto(comments comments);
    List<comments> toListEntity(List<CommentDto> commentDtos);
    List<CommentDto>toListDto(List<comments> comments);
}
