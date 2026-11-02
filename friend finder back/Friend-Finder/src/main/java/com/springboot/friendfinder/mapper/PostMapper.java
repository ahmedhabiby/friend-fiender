package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.model.post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    post toEntity(postDto postDto);
    postDto toDto(post post);
    List<post>toListEntity(List<postDto> postDto);
    List<postDto>toListDto(List<post> post);
}
