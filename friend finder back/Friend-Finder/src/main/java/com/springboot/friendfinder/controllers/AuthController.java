package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.loginVm.RequestVm;
import com.springboot.friendfinder.controllers.loginVm.ResponseVm;
import com.springboot.friendfinder.controllers.signupVm.SignupRequest;
import com.springboot.friendfinder.controllers.signupVm.SignupResponse;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.service.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.util.List;
import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(
        name = "Authentication Controller"
)
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if it login Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in login ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseVm> login(@Valid @RequestBody RequestVm requestVm) throws URISyntaxException{
        return  ResponseEntity.created(new URI("/login")).body(authService.login(requestVm));
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if it signup Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in signup ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest)  throws URISyntaxException {
        return  ResponseEntity.created(new URI("/signup")).body(authService.signup(signupRequest));
    }

   
    
}
