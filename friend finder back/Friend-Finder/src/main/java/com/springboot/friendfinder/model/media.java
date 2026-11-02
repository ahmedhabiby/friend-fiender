package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.friendfinder.enums.mediaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class media extends BaseEntity {
    private String mediaUrl;
    @Enumerated(EnumType.STRING)
    private mediaEnum mediaType;
    @ManyToOne
    @JsonBackReference
    private post post;
}
