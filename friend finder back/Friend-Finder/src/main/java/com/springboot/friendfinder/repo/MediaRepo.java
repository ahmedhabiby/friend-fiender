package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepo extends JpaRepository<media,Long> {
}
