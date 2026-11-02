package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import com.springboot.friendfinder.controllers.postVm.LatestPost;
import com.springboot.friendfinder.controllers.postVm.PostRequest;
import com.springboot.friendfinder.controllers.postVm.PostResponse;
import com.springboot.friendfinder.enums.mediaEnum;
import com.springboot.friendfinder.model.PostReaction;
import com.springboot.friendfinder.repo.CommentRepo;
import com.springboot.friendfinder.repo.MediaRepo;
import com.springboot.friendfinder.repo.PostReactionRepo;
import com.springboot.friendfinder.repo.PostRepo;
import com.springboot.friendfinder.dto.MediaDto;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.MediaMapper;
import com.springboot.friendfinder.mapper.PostMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.media;
import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.service.PostService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostMapper postMapper;
    private PostRepo postRepo;
    private UserService userService;
    private UserMapper userMapper;
    private MediaRepo  mediaRepo;
    private MediaMapper mediaMapper;
    private CommentRepo commentRepo;
    private PostReactionRepo postReactionRepo;

    @Autowired
    public PostServiceImpl(PostMapper postMapper,CommentRepo commentRepo, PostRepo postRepo, UserService userService, UserMapper userMapper, MediaRepo mediaRepo, MediaMapper mediaMapper,PostReactionRepo postReactionRepo) {
        this.postMapper = postMapper;
        this.mediaRepo=mediaRepo;
        this.userMapper = userMapper;
        this.postRepo = postRepo;
        this.userService = userService;
        this.mediaMapper = mediaMapper;
        this.commentRepo = commentRepo;
        this.postReactionRepo = postReactionRepo;
    }
    @Override
    public postDto savePost(PostRequest postRequest) {
       if (Objects.isNull(postRequest)) {
           throw new RuntimeException("Post must not be null");
       }
       postDto postDto = new postDto();
       postDto.setContent(postRequest.getContent());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String username= authentication.getName();
       usersDto usersDto = userService.findUserByUsername(username);
       users user=userMapper.toEntity(usersDto);
       postDto.setUsers(user);
        MediaDto mediaDto = new MediaDto();
        mediaDto.setMediaUrl(postRequest.getMediaUrl());
        if(postRequest.getMediaType().equals("image")){
            mediaDto.setMediaType(mediaEnum.Image);
        }
        else if(postRequest.getMediaType().equals("video")) {
            mediaDto.setMediaType(mediaEnum.Video);
        }

        media media=mediaMapper.toEntity(mediaDto);
        postDto.setMedia(List.of(media));
        post post = postMapper.toEntity(postDto);
        media.setPost(post);
        postRepo.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public postDto findPostById(Long id) {
        return postMapper.toDto(postRepo.findById(id).get());
    }

    @Override
    public List<PostResponse> findAllPosts() {
        List<post> posts = postRepo.findAll();

        return posts.stream().map(post -> {
            PostResponse postResponse = new PostResponse();
            postResponse.setPostContent(post.getContent());
            if(post.getUsers().getProfile()!=null){
                postResponse.setImageUrl(post.getUsers().getProfile().getImageUrl());
            }
            postResponse.setPostAuthor(post.getUsers().getUsername());
            postResponse.setNumberOfLikes(commentRepo.countLikesReaction(post.getId()));
            postResponse.setCreatedAt(post.getCreatedAt());
            postResponse.setNumberOfDislikes(commentRepo.countDisLikesReaction(post.getId()));
            postResponse.setMediaUrl(
                    (post.getMedia()
                            .stream()
                            .map(media::getMediaUrl)
                            .collect(Collectors.toList()))
            );

            postResponse.setPostId(post.getId());
            // comments mapping
            List<ResponseComment> commentResponses =
                    post.getComments().stream().map(c ->
                            new ResponseComment(
                                    c.getId(),
                                    c.getContent(),
                                    c.getUser().getId(),
                                    c.getUser().getUsername(),
                                    post.getId(),
                                    c.getCreatedAt(),
                                    c.getUser().getProfile() != null ? c.getUser().getProfile().getImageUrl() : null
                            )
                    ).toList();



            postResponse.setResponseComments(commentResponses);
            return postResponse;
        }).toList();

    }

    @Override
    public List<PostResponse> findAllPostsByLoginUser() {
        List<post> posts = postRepo.findAll();

        return posts
                .stream()
                .filter(post -> post.getUsers().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
                .map(post -> {
            PostResponse postResponse = new PostResponse();
            postResponse.setPostContent(post.getContent());
            postResponse.setPostAuthor(post.getUsers().getUsername());
            postResponse.setNumberOfLikes(commentRepo.countLikesReaction(post.getId()));
            postResponse.setCreatedAt(post.getCreatedAt());
            postResponse.setNumberOfDislikes(commentRepo.countDisLikesReaction(post.getId()));
            postResponse.setMediaUrl(
                    (post.getMedia()
                            .stream()
                            .map(media::getMediaUrl)
                            .collect(Collectors.toList()))
            );

            postResponse.setPostId(post.getId());
            // comments mapping
            List<ResponseComment> commentResponses =
                    post.getComments().stream().map(c ->
                            new ResponseComment(
                                    c.getId(),
                                    c.getContent(),
                                    c.getUser().getId(),
                                    c.getUser().getUsername(),
                                    post.getId(),
                                    c.getCreatedAt(),
                                    c.getUser().getProfile() != null ? c.getUser().getProfile().getImageUrl() : null
                            )
                    ).toList();



            postResponse.setResponseComments(commentResponses);
            return postResponse;
        }).toList();
    }

    @Override
    public LatestPost findLatedPostByLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        usersDto usersDto = userService.findUserByUsername(username);
        users user=userMapper.toEntity(usersDto);
        post post   =postRepo.latestPostForLoginUser(user.getUsername());
        return new LatestPost(post.getCreatedAt().toString());
    }

   @Override
public LatestPost findLatestPostReactionByLoginUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    usersDto usersDto = userService.findUserByUsername(username);
    users user = userMapper.toEntity(usersDto);

    PostReaction postReaction = postReactionRepo.findLatestPostReaction(user.getId());

    if (postReaction == null) {
        return new LatestPost("");
    }

    return new LatestPost(postReaction.getCreatedAt().toString());
}
    @Override
    public Long countAllPosts() {
        return postRepo.count();
    }
    @Override
    public List<PostResponse> findAllPostsOrderByCreatedAtDesc() {
        List<post> posts = postRepo.findAllPostsOrderByCreatedAtDesc();

        return posts.stream().map(post -> {
            PostResponse postResponse = new PostResponse();
            postResponse.setPostContent(post.getContent());
            postResponse.setPostAuthor(post.getUsers().getUsername());
            postResponse.setNumberOfLikes(commentRepo.countLikesReaction(post.getId()));
            postResponse.setCreatedAt(post.getCreatedAt());
            postResponse.setNumberOfDislikes(commentRepo.countDisLikesReaction(post.getId()));
            postResponse.setMediaUrl(
                    (post.getMedia()
                            .stream()
                            .map(media::getMediaUrl)
                            .collect(Collectors.toList()))
            );

            postResponse.setPostId(post.getId());
            // comments mapping
            List<ResponseComment> commentResponses =
                    post.getComments().stream().map(c ->
                            new ResponseComment(
                                    c.getId(),
                                    c.getContent(),
                                    c.getUser().getId(),
                                    c.getUser().getUsername(),
                                    post.getId(),
                                    c.getCreatedAt(),
                                    c.getUser().getProfile() != null ? c.getUser().getProfile().getImageUrl() : null

                            )
                    ).toList();
            postResponse.setResponseComments(commentResponses);
            return postResponse;
        }).toList();
    }
}
