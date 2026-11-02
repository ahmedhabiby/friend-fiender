package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.contactVm.ContactResponse;
import com.springboot.friendfinder.dto.ContactDto;

public interface ContactService {
    ContactResponse save(ContactDto contactDto);
}
