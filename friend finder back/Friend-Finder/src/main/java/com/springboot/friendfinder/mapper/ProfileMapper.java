package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.ContactDto;
import com.springboot.friendfinder.dto.ProfileDto;
import com.springboot.friendfinder.model.Contact;
import com.springboot.friendfinder.model.profile;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    profile toEntity(ProfileDto profileDto);
    ProfileDto toDto(profile profile);
    List<profile> toListEntity(List<ProfileDto> profileDtos);
    List<ProfileDto>toListDto(List<profile> profiles);
}
