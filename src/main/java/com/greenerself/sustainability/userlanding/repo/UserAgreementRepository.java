package com.greenerself.sustainability.userlanding.repo;

import com.greenerself.sustainability.userlanding.entity.UserAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {
}
