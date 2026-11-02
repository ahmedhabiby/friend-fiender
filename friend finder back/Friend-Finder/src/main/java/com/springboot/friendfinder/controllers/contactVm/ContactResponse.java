package com.springboot.friendfinder.controllers.contactVm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class ContactResponse implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String message;
}
