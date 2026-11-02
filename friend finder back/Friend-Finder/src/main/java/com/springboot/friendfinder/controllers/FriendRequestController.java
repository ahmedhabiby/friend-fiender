package com.springboot.friendfinder.controllers;

import com.springboot.friendfinder.controllers.friendRequestVm.*;
import com.springboot.friendfinder.controllers.userFriends.UserFriends;
import com.springboot.friendfinder.service.FriendRequestService;
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
@Tag(
        name = "Friend Request Controller"
)
public class FriendRequestController {
    private FriendRequestService friendRequestService;
    @Autowired
    public void setFriendRequestService(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }
    @PostMapping("/sendRequest")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if Request sended Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in sending ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<FriendResponseVm> sendRequest(@RequestBody @Valid FriendRequestVm friendRequestVm) throws URISyntaxException {
        return ResponseEntity.created(new URI("/sendRequest")).body(friendRequestService.friendRequest(friendRequestVm));
    }
    @GetMapping("getAllRequests")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if Request recieved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in getting requests ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<List<FriendResponseVm>> getAllRequests(@RequestBody @Valid FriendRequestVm request, @RequestParam @Schema(example = "accept") String status){
        return ResponseEntity.ok(friendRequestService.getALLFriendRequest(request,status));
    }
    @PostMapping("/acceptRequest")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if request accepted Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in accepting ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<FriendResponseVm> acceptRequest(@RequestParam @Schema(example = "100") long senderId, @RequestParam @Schema(example = "100")  long receiverId) throws URISyntaxException {
        return ResponseEntity.created(new URI("/acceptRequest")).body(friendRequestService.acceptFriendRequest(senderId,receiverId));
    }
    @PostMapping("/rejectRequest")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201 created",
                    description = "if request rejected Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "406 Not Acceptable",
                    description = "if it has error in rejecting ",
                    content = @Content(
                            schema = @Schema()
                    )
            )
    })
    public ResponseEntity<FriendResponseVm> rejectRequest(@RequestParam @Schema(example = "100")  long senderId, @RequestParam @Schema(example = "100")  long receiverId) throws URISyntaxException {
        return ResponseEntity.created(new URI("/rejectRequest")).body(friendRequestService.rejectFriendRequest(senderId,receiverId));
    }
    @GetMapping("/allNotFollowedUsers")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if find friends not followed by this user  Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
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
    public ResponseEntity<FriendsNotFollowed> friendsNotFollowed(){
        return ResponseEntity.ok(friendRequestService.friendsNotFollowed());
    }
    @GetMapping("/aboutLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if find about  user  Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
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
    public ResponseEntity<NumberOfUserFollower> aboutLoginUser(){
        return ResponseEntity.ok(friendRequestService.aboutUserLogin());
    }
    @GetMapping("/getAllFriendResponseForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if find friends  followed by this user  Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
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
    public ResponseEntity<List<FriendResponseForLoginUser>> getAllFriendResponseForLoginUser(){
        return ResponseEntity.ok(friendRequestService.getAllFriendResponseForLoginUser());
    }
    @GetMapping("/getAllFriendsForLoginUser")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200 ok",
                    description = "if find friends  followed by this user  Successfully",
                    content = @Content(
                            schema = @Schema(implementation = FriendResponseVm.class)
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
    public ResponseEntity<List<UserFriends>> getAllFriendsForLoginUser(){
        return ResponseEntity.ok(friendRequestService.getAllFriendsForLoginUser());
    }
}
