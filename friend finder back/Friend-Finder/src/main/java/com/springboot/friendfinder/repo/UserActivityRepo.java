package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepo extends JpaRepository<UserActivity,Long> {
}
