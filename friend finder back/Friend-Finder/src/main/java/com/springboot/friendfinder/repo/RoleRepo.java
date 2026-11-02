package com.springboot.friendfinder.repo;

import com.springboot.friendfinder.model.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<roles,Long> {
    @Query(value = "select * from roles where role_name=:name",nativeQuery = true)
    roles findRolesByName(String name);
}
