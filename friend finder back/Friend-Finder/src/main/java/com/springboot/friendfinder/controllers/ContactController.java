package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import com.springboot.friendfinder.controllers.contactVm.ContactResponse;
import com.springboot.friendfinder.dto.ContactDto;
import com.springboot.friendfinder.service.ContactService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Tag(
        name = "Contact Controller"
)
public class ContactController {
    private ContactService contactService;
    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/saveContact")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if contact saved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ContactResponse.class)
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
    public ResponseEntity<ContactResponse> saveContact(@RequestBody @Valid ContactDto contactDto) throws URISyntaxException {
        return ResponseEntity.created(new URI("/saveContact")).body(contactService.save(contactDto));
    }
}
