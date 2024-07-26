package com.greenerself.sustainability.userlanding.repo;

import com.greenerself.sustainability.userlanding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
}
