package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.commentVm.RequestComment;
import com.springboot.friendfinder.controllers.commentVm.ResponseComment;
import com.springboot.friendfinder.dto.CommentDto;

public interface CommentService {
    ResponseComment saveComment(RequestComment requestComment);
    ResponseComment findLatestCommentForLoginUser();
    ResponseComment updateComment(ResponseComment requestComment);
    void deleteComment(Long commentId);
}
