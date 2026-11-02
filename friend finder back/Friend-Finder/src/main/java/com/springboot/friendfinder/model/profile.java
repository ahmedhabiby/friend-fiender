package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class profile extends BaseEntity {
    private String imageUrl;
    private String location;
    private String job;
    private String workExperience;
    private String interest;
    private String language;
    @OneToOne
    @JsonBackReference("profiles")
    private users user;
}
