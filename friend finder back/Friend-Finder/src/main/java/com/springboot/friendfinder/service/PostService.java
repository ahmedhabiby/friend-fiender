package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.postVm.LatestPost;
import com.springboot.friendfinder.controllers.postVm.PostRequest;
import com.springboot.friendfinder.controllers.postVm.PostResponse;
import com.springboot.friendfinder.dto.postDto;

import java.util.List;

public interface PostService {
    postDto savePost(PostRequest postRequest);
    postDto findPostById(Long id);
    List<PostResponse> findAllPosts();
    List<PostResponse> findAllPostsByLoginUser();
    LatestPost findLatedPostByLoginUser();
    LatestPost findLatestPostReactionByLoginUser();
    Long countAllPosts();
    List<PostResponse> findAllPostsOrderByCreatedAtDesc();

}
