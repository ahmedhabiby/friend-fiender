package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class users extends BaseEntity {
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<roles> roles;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<post> posts;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("userComment")
    private List<comments> comments;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("userReact")
    private List<PostReaction>  reactions;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("userContact")
    private List<Contact> contacts;
    @OneToMany(mappedBy = "sender")
    @JsonManagedReference("senders")
    private List<FriendRequest> senders;
    @OneToMany(mappedBy = "receiver")
    @JsonManagedReference("receivers")
    private List<FriendRequest> receivers;
    @OneToOne(mappedBy = "user")
    @JsonManagedReference("profiles")
    private profile profile;
}
