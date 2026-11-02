package com.springboot.friendfinder.controllers.loginVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class RequestVm implements Serializable {
    @Schema(example = "ahmed")
    @NotEmpty(message = "username.notempty")
    private String username;
    @Schema(example = "Ahmed123@*#")
    @NotEmpty(message = "password.notempty")
    private String password;
}
