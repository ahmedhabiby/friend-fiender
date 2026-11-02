package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.reactionVm.ReactionRequest;
import com.springboot.friendfinder.repo.PostReactionRepo;
import com.springboot.friendfinder.dto.PostReactionDto;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.PostMapper;
import com.springboot.friendfinder.mapper.PostReactionMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.PostReaction;
import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.service.PostReactionService;
import com.springboot.friendfinder.service.PostService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PostReactionServiceImpl implements PostReactionService {
    private PostMapper postMapper;
    private PostService postService;
    private UserMapper userMapper;
    private PostReactionMapper postReactionMapper;
    private PostReactionRepo postReactionRepo;
    private UserService userService;
    @Autowired
    public PostReactionServiceImpl(PostReactionMapper postReactionMapper,UserService userService,PostReactionRepo postReactionRepo,PostMapper postMapper,UserMapper userMapper,PostService postService) {
        this.postMapper = postMapper;
        this.postReactionMapper = postReactionMapper;
        this.userMapper = userMapper;
        this.postReactionRepo = postReactionRepo;
        this.postService = postService;
        this.userService = userService;
    }
    @Override
    @CachePut(value = "react",key = "'react'+#result.id")
    public PostReactionDto doReaction(ReactionRequest reactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto =userService.findUserByUsername(username);
        users user=userMapper.toEntity(usersDto);
        postDto postDto= postService.findPostById(reactionRequest.getPostId());
        post post=postMapper.toEntity(postDto);
        PostReaction existingReaction = postReactionRepo.findByPostIdAndUserId(post.getId(), user.getId());
        if (existingReaction != null) {
            updateReaction(post.getId(), user.getId());
            return postReactionMapper.toDto(existingReaction);
        }
        PostReaction postReaction=new PostReaction();
        postReaction.setPost(post);
        postReaction.setUser(user);
        postReaction.setReaction(reactionRequest.getReaction());
        postReactionRepo.save(postReaction);
        return postReactionMapper.toDto(postReaction);
    }
    @Override
    public void updateReaction(Long postId, Long userId) {
        PostReaction postReaction = postReactionRepo.findByPostIdAndUserId(postId, userId);
        if (postReaction != null && postReaction.getReaction()==1) {
            postReaction.setReaction(-1);
            postReactionRepo.save(postReaction);
        }
         else if (postReaction != null && postReaction.getReaction()==-1) {
            postReaction.setReaction(1);
            postReactionRepo.save(postReaction);
        }
       
    }
    @Override
    public void deleteReaction(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto =userService.findUserByUsername(username);
        PostReaction postReaction = postReactionRepo.findByPostIdAndUserId(postId, usersDto.getId());
        if (postReaction != null) {
            postReactionRepo.delete(postReaction);
        }
    }
    @Override
    public PostReactionDto findReaction(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto =userService.findUserByUsername(username);
        PostReaction postReaction = postReactionRepo.findByPostIdAndUserId(postId, usersDto.getId());
            return postReactionMapper.toDto(postReaction);
      
    }

   
}
