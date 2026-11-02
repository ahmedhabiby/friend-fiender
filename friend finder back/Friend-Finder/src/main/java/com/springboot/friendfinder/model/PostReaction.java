package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"post_id","user_id"}))
public class PostReaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("userReact")
    private users user;
    private int reaction;
}
