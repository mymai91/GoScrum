package com.GoScrum.GoScrumApi.repository;

import com.GoScrum.GoScrumApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
