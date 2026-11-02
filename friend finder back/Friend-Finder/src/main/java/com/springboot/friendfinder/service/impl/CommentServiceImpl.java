package com.springboot.friendfinder.service.impl;

import com.springboot.friendfinder.controllers.commentVm.RequestComment;
import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import com.springboot.friendfinder.dto.CommentDto;
import com.springboot.friendfinder.mapper.CommentMapper;
import com.springboot.friendfinder.repo.CommentRepo;
import com.springboot.friendfinder.dto.postDto;
import com.springboot.friendfinder.dto.usersDto;
import com.springboot.friendfinder.mapper.PostMapper;
import com.springboot.friendfinder.mapper.UserMapper;
import com.springboot.friendfinder.model.comments;
import com.springboot.friendfinder.model.post;
import com.springboot.friendfinder.model.users;
import com.springboot.friendfinder.service.CommentService;
import com.springboot.friendfinder.service.PostService;
import com.springboot.friendfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private UserMapper userMapper;
    private UserService userService;
    private PostService postService;
    private PostMapper postMapper;
    private CommentMapper commentMapper;

    @Autowired
    CommentServiceImpl(CommentRepo commentRepo,CommentMapper commentMapper ,UserMapper userMapper, UserService userService, PostService postService, PostMapper postMapper) {
        this.commentRepo = commentRepo;
        this.userMapper = userMapper;
        this.userService = userService;
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }
    @Override
    @CacheEvict(value = "comment", allEntries = true)
    public ResponseComment saveComment(RequestComment requestComment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto= userService.findUserByUsername(username);
        users user = userMapper.toEntity(usersDto);
        postDto postDto= postService.findPostById(requestComment.getPostId());
        post post = postMapper.toEntity(postDto);
        comments comment=new comments();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(requestComment.getContent());
        commentRepo.save(comment);
        return new ResponseComment(comment.getId(), comment.getContent(), comment.getUser().getId(),comment.getUser().getUsername(),comment.getPost().getId(),comment.getCreatedAt(),comment.getUser().getProfile() != null ? comment.getUser().getProfile().getImageUrl() : null);

    }

    @Override
    @CachePut(value = "comment",key = "'search'+#result.comment_id")
    public ResponseComment findLatestCommentForLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        usersDto usersDto= userService.findUserByUsername(username);
        users user = userMapper.toEntity(usersDto);
        comments comment=commentRepo.findLatestComment(user.getUsername());
        return new ResponseComment(comment.getId(), comment.getContent(), comment.getUser().getId(),comment.getUser().getUsername(),comment.getPost().getId(),comment.getCreatedAt(),comment.getUser().getProfile() != null ? comment.getUser().getProfile().getImageUrl() : null);
    }
    @Override
    public ResponseComment updateComment(ResponseComment requestComment) {
        comments comment = commentRepo.findById(requestComment.getComment_id()).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(requestComment.getComment_content());
        commentRepo.save(comment);
        return new ResponseComment(comment.getId(), comment.getContent(), comment.getUser().getId(),comment.getUser().getUsername(),comment.getPost().getId(),comment.getCreatedAt(),comment.getUser().getProfile() != null ? comment.getUser().getProfile().getImageUrl() : null);
    }
        
    @Override
    public void deleteComment(Long commentId) {
        comments comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
    }

}
