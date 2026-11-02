package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.dto.ProfileDto;
import com.springboot.friendfinder.model.profile;
import com.springboot.friendfinder.service.ProfileService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Tag(
        name = "Profile Controller"
)
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/saveProfile")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if profile saved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ProfileDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in saving ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<ProfileDto> saveProfile(@RequestBody @Valid ProfileDto profileDto) throws URISyntaxException {
        return ResponseEntity.created(new URI("/saveProfile")).body(profileService.saveProfile(profileDto));
    }
    @GetMapping("/getProfileForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting pprofile for login user Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ProfileDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in getting ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<ProfileDto> getProfileForLoginUser(){
        return ResponseEntity.ok(profileService.getProfileForLoginUser());
    }
}
