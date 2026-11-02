package com.springboot.friendfinder.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserActivity extends BaseEntity {
    private String username;
    private String action;
    private String endpoint;
    private String ipAddress;
    private String userAgent;
}

