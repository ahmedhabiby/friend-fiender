package com.springboot.friendfinder.mapper;

import com.springboot.friendfinder.dto.ContactDto;
import com.springboot.friendfinder.model.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toEntity(ContactDto contactDto);
    ContactDto toDto(Contact contacts);
    List<Contact> toListEntity(List<ContactDto> contactDtos);
    List<ContactDto>toListDto(List<Contact> contacts);
}
