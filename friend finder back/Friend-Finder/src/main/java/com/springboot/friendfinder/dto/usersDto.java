package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.roles;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.List;

@Setter
@Getter
public class usersDto {
    private Long id;
    private String username;
    private String email;
    @NotEmpty(message = "password.notempty")
    private String password;
    private List<roles> roles;
    private List<post> posts;

}
