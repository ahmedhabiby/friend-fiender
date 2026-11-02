package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<post,Long> {

    @Query(value = "SELECT c FROM post c where c.users.username=:loginUser ORDER BY c.createdAt DESC FETCH FIRST 1 ROWS ONLY")
    post latestPostForLoginUser(String loginUser);
    @Query("""
            select c from post c
            order by c.createdAt desc
            FETCH FIRST 10 ROWS ONLY
            """)
    List<post> findAllPostsOrderByCreatedAtDesc();
}
