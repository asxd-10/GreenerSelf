package com.greenerself.sustainability.userlanding.repo;

import com.greenerself.sustainability.userlanding.entity.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Long> {
}
