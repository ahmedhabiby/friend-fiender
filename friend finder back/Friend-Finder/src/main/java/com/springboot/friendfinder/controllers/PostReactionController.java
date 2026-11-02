package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.reactionVm.ReactionRequest;
import com.springboot.friendfinder.dto.PostReactionDto;
import com.springboot.friendfinder.service.PostReactionService;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Tag(
        name = " Post Reaction Controller"
)
public class PostReactionController {
    private PostReactionService postReactionService;
    @Autowired
    public PostReactionController(PostReactionService postReactionService) {
        this.postReactionService = postReactionService;
    }
    @PostMapping("/saveReaction")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if Request PostReaction saved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = PostReactionDto.class)
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
    public ResponseEntity<PostReactionDto> saveReaction(@RequestBody @Valid ReactionRequest reactionRequest) throws URISyntaxException {
        return ResponseEntity.created(new URI("/saveReaction")).body(postReactionService.doReaction(reactionRequest));
    }

     @DeleteMapping("/deleteReaction")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "204 No Content",
                        description = "if Request PostReaction deleted Successfully",
                        content = @Content(
                                schema = @Schema()
                        )
                ),
                @ApiResponse(
                        responseCode = "406 Not Acceptable",
                        description = "if it has error in deleting ",
                        content = @Content(
                                schema = @Schema()
                        )
                )
    })
    public ResponseEntity<Void> deleteReactionEndpoint(@Param("postId") Long postId) {
        postReactionService.deleteReaction(postId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getPostReaction")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200 ok",
                        description = "if Request PostReaction found Successfully",
                        content = @Content(
                                schema = @Schema(implementation = PostReactionDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "406 Not Acceptable",
                        description = "if it has error in finding ",
                        content = @Content(
                                schema = @Schema()
                        )
                )
        })
    public ResponseEntity<PostReactionDto> getPostReaction(@RequestParam Long postId) {
        PostReactionDto postReactionDto = postReactionService.findReaction(postId);
            return ResponseEntity.ok(postReactionDto);
        
    }
    
}
