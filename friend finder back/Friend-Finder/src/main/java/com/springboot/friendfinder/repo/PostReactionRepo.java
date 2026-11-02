package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.PostReaction;
import com.springboot.friendfinder.model.comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepo extends JpaRepository<PostReaction,Long> {
    @Query(value = "SELECT c FROM PostReaction c where c.user.id=:id ORDER BY c.createdAt DESC FETCH FIRST 1 ROWS ONLY")
    PostReaction findLatestPostReaction(long id);
    @Query(value = "SELECT c FROM PostReaction c where c.post.id=:postId and c.user.id=:userId")
    PostReaction findByPostIdAndUserId(Long postId, Long userId);
}
