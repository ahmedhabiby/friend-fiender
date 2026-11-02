package com.springboot.friendfinder.controllers.signupVm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SignupRequest implements Serializable {
    @Schema(example = "ahmed")
    @NotEmpty(message = "username.notempty")
    private String username;
    @Schema(example = "Ahmed123@*#")
    @NotEmpty(message = "password.notempty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "password.invalid"
    )
    private String password;
    @Schema(example = "ahmed@gmail.com")
    @NotEmpty(message = "email.notempty")
    private String email;
}
