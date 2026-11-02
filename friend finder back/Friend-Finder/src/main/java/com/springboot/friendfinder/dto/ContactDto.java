package com.springboot.friendfinder.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.friendfinder.model.users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ContactDto implements Serializable {
    @Schema(example = "1200")
    private Long id;
    @Schema(example = "Ahmed")
    @NotEmpty(message = "name.notempty")
    private String name;
    @Schema(example = "ahmed@gmail.com")
    @NotEmpty(message = "email.notempty")
    private String email;
    @Schema(example = "+201001205992")
    @NotEmpty(message = "phone.notempty")
    private String phone;
    @Schema(example = "i have a problem")
    @NotEmpty(message = "message.notempty")
    private String message;
    private users user;
}
