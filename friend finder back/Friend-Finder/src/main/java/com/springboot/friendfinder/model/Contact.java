package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Contact extends BaseEntity {
    private String name;
    private String email;
    private String phone;
    private String message;
    @ManyToOne
    @JsonBackReference("userContact")
    private users user;
}
