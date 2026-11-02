package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.postVm.LatestPost;
import com.springboot.friendfinder.controllers.postVm.PostRequest;
import com.springboot.friendfinder.controllers.postVm.PostResponse;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.service.PostService;
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
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Tag(
        name = "Post Controller"
)
public class PostController {
    private PostService postService;
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/savePost")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if post saved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = postDto.class)
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
    public ResponseEntity<postDto> savePost(@RequestBody @Valid PostRequest postRequest) throws URISyntaxException {
        return ResponseEntity.created(new URI("/savePost")).body(postService.savePost(postRequest));
    }
    @GetMapping("/getAllPost")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting all posts Successfully",
                    content = @Content(
                            schema = @Schema(implementation = PostResponse.class)
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
    public ResponseEntity<List<PostResponse>> getAllPost() {
        return ResponseEntity.ok().body(postService.findAllPosts());
    }
    @GetMapping("/getAllPostForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting all posts for login user Successfully",
                    content = @Content(
                            schema = @Schema(implementation = PostResponse.class)
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
    public ResponseEntity<List<PostResponse>> getAllPostForLoginUser() {
        return ResponseEntity.ok().body(postService.findAllPostsByLoginUser());
    }
    @GetMapping("/getLatestPostForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting latest posts for login user Successfully",
                    content = @Content(
                            schema = @Schema(implementation = LatestPost.class)
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
    public ResponseEntity<LatestPost> getLatestPostForLoginUser() {
        return ResponseEntity.ok(postService.findLatedPostByLoginUser());
    }

    @GetMapping("/getLatestPostReactionForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting latest post reactions for login user Successfully",
                    content = @Content(
                            schema = @Schema(implementation = LatestPost.class)
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
    public ResponseEntity<LatestPost> getLatestPostReactionForLoginUser() {
        return ResponseEntity.ok(postService.findLatestPostReactionByLoginUser());
    }
    @GetMapping("/countAllPosts")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if getting count of all posts Successfully",
                    content = @Content(
                            schema = @Schema(implementation = Long.class)
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
    public ResponseEntity<Long> countAllPosts() {
        return ResponseEntity.ok(postService.countAllPosts());
    }

    @GetMapping("/latestPost")
    public ResponseEntity<List<PostResponse>> findAllPostsOrderByCreatedAtDesc() {
        return ResponseEntity.ok(postService.findAllPostsOrderByCreatedAtDesc());
    }
    
}
