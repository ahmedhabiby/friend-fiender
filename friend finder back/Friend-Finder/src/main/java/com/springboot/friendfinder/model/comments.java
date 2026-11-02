package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class comments extends BaseEntity {
    private String content;
    @ManyToOne
    @JsonBackReference
    private post post;
    @ManyToOne
    @JsonBackReference("userComment")
    private users user;
}
