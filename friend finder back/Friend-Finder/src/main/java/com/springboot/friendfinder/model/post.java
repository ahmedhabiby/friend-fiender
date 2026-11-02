package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comments;

import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
public class post extends BaseEntity{
    private String content ;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private users users;
    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<media> media;
    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<comments> comments;
    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostReaction>  reactions;

}
