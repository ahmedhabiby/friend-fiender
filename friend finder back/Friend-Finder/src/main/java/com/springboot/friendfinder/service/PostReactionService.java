package com.springboot.friendfinder.service;

import com.springboot.friendfinder.controllers.reactionVm.ReactionRequest;
import com.springboot.friendfinder.dto.PostReactionDto;

public interface PostReactionService {
    PostReactionDto doReaction(ReactionRequest reactionRequest);
    void updateReaction(Long postId, Long userId);
    void deleteReaction(Long postId);
    PostReactionDto findReaction(Long postId);
}
