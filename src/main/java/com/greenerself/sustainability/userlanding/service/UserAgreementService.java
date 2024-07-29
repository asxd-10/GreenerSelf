package com.greenerself.sustainability.userlanding.service;

import com.greenerself.sustainability.userlanding.entity.UserAgreement;
import com.greenerself.sustainability.userlanding.repo.UserAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAgreementService {

    @Autowired
    private UserAgreementRepository userAgreementRepository;

    public void saveUserAgreement(UserAgreement agreement) {
        userAgreementRepository.save(agreement);
    }
}
