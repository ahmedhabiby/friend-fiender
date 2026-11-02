package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<users, Integer> {
    @Query(value = "select * from users where username=:username",nativeQuery = true)
    users findUserByUsername(String username);

}
