package com.springboot.friendfinder.controllers.friendRequestVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class FriendsNotFollowed implements Serializable {
    @Schema(example = "[ahmed,mohamed]")
    List<String>userNames;
    @Schema(example = "[http://example.com/image1.jpg,http://example.com/image2.jpg]")
    List<String>imageUrls;

}
