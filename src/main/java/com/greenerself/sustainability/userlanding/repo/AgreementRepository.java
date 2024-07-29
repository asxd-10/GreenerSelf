package com.greenerself.sustainability.userlanding.repo;

import com.greenerself.sustainability.userlanding.entity.Agreement;
import com.greenerself.sustainability.userlanding.entity.UserAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Agreement findTopByTypeOrderByVersionDesc(String type);
}
