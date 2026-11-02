package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.MediaDto;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.model.media;
import com.springboot.friendfinder.model.post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    media toEntity(MediaDto mediaDto);
    MediaDto toDto(media media);
    List<media> toListEntity(List<MediaDto> mediaDtos);
    List<MediaDto>toListDto(List<media> media);
}
