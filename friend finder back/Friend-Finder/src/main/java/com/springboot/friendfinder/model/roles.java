package com.springboot.friendfinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class roles extends BaseEntity{
    private String roleName;
    @ManyToMany
    private List<users> users;
}
