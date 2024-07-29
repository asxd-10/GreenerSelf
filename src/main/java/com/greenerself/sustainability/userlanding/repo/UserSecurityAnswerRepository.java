package com.greenerself.sustainability.userlanding.repo;

import com.greenerself.sustainability.userlanding.entity.UserSecurityAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecurityAnswerRepository extends JpaRepository<UserSecurityAnswer, Long> {
}
