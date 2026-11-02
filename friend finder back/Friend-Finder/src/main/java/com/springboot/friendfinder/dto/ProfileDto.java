package com.springboot.friendfinder.dto;

import com.springboot.friendfinder.model.users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ProfileDto implements Serializable {
    @Schema(example = "bref about you")
    @NotEmpty(message = "personalInfo.notempty")
    private String personalInfo;

    @NotEmpty(message = "imageUrl.notempty")
    @Schema(example = "aa.jpg")
    private String imageUrl;

    @NotEmpty(message = "location.notempty")
    @Schema(example = "cairo")
    private String location;

    @NotEmpty(message = "job.notempty")
    @Schema(example = "doctor")
    private String job;

    @NotEmpty(message = "workExperience.notempty")
    @Schema(example = "+5 years")
    private String workExperience;

    @NotEmpty(message = "interest.notempty")
    @Schema(example = "football")
    private String interest;

    @NotEmpty(message = "language.notempty")
    @Schema(example = "english")
    private String language;

    private users user;

}
