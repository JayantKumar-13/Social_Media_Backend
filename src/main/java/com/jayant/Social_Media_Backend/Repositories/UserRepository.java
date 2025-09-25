package com.jayant.Social_Media_Backend.Repositories;

import com.jayant.Social_Media_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
