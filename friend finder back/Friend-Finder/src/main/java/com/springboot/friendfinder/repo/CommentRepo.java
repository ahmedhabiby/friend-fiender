package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.comments;
import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<comments,Long> {
    @Query("SELECT COUNT(r) FROM PostReaction r WHERE r.post.id = :postId AND r.reaction = 1")
    long countLikesReaction(@Param("postId") Long postId);
    @Query("SELECT COUNT(r) FROM PostReaction r WHERE r.post.id = :postId AND r.reaction = -1")
    long countDisLikesReaction(@Param("postId") Long postId);
    @Query(value = "SELECT c FROM comments c where c.user.username=:loginUser ORDER BY c.createdAt DESC FETCH FIRST 1 ROWS ONLY")
    comments findLatestComment(String loginUser);
}
