package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<profile,Long> {
    @Query("SELECT p FROM profile p WHERE p.user.id = :id")
    profile findprofileByUserId(Long id);

}
