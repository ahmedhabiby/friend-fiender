package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.commentVm.RequestComment;
import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import com.springboot.friendfinder.controllers.loginVm.ResponseVm;
import com.springboot.friendfinder.service.CommentService;
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
        name = "Comment Controller"
)
public class CommentController {
    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/saveComment")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if comment saved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseComment.class)
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
    public ResponseEntity<ResponseComment> saveComment(@RequestBody @Valid RequestComment requestComment) throws URISyntaxException {
    return ResponseEntity.created(new URI("/saveComment")).body(commentService.saveComment(requestComment));
    }
    @GetMapping("/searchLatestCommentForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if comment founded Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseComment.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in finding comment ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<ResponseComment> searchLatestCommentForLoginUser()  {
        return ResponseEntity.ok(commentService.findLatestCommentForLoginUser());
    }

        @PutMapping("/updateComment")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200 ok",
                        description = "if comment updated Successfully",
                        content = @Content(
                                schema = @Schema(implementation = ResponseComment.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "406 Not Acceptable",
                        description = "if it has error in updating comment ",
                        content = @Content(
                                schema = @Schema()
                        )
                )
        })
        public ResponseEntity<ResponseComment> updateComment(@RequestBody @Valid ResponseComment requestComment) {
            return ResponseEntity.ok(commentService.updateComment(requestComment));
        }

        @DeleteMapping("/deleteComment")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200 ok",
                        description = "if comment deleted Successfully",
                        content = @Content(
                                schema = @Schema(implementation = ResponseVm.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "406 Not Acceptable",
                        description = "if it has error in deleting comment ",
                        content = @Content(
                                schema = @Schema()
                        )
                )
        })      

public ResponseEntity<ResponseVm> deleteComment(@RequestParam Long comment_id) {
            commentService.deleteComment(comment_id);
            return ResponseEntity.ok(new ResponseVm("Comment deleted successfully"));
        }
}
