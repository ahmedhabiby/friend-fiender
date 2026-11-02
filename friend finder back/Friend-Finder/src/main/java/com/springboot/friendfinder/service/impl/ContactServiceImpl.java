package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.contactVm.ContactResponse;
import com.springboot.friendfinder.repo.ContactRepo;
import com.springboot.friendfinder.dto.ContactDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.ContactMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.Contact;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.service.ContactService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepo contactRepo;
    private ContactMapper contactMapper;
    private UserService userService;
    private UserMapper userMapper;
    @Autowired
    public ContactServiceImpl(ContactRepo contactRepo, ContactMapper contactMapper, UserService userService, UserMapper userMapper) {
        this.contactRepo = contactRepo;
        this.contactMapper = contactMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @Override
    @CacheEvict(value = "contact", allEntries = true)
    public ContactResponse save(ContactDto contactDto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        usersDto usersDto = userService.findUserByUsername(username);
        users user = userMapper.toEntity(usersDto);

        contactDto.setUser(user);

        Contact contact = contactMapper.toEntity(contactDto);

        Contact savedContact = contactRepo.save(contact);

        return new ContactResponse(
                savedContact.getName(),
                savedContact.getEmail(),
                savedContact.getPhone(),
                savedContact.getMessage()
        );
    }

}
