package com.springboot.friendfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.friendfinder.enums.FriendsEnums;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"sender_id","receiver_id"}))
public class FriendRequest extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference("senders")
    private users sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference("receivers")
    private users receiver;
    @Enumerated(EnumType.STRING)
    private FriendsEnums status;
}
