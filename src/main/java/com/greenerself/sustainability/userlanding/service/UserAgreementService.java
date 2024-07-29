package com.greenerself.sustainability.userlanding.service;

import com.greenerself.sustainability.userlanding.entity.Agreement;
import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.userlanding.entity.UserAgreement;
import com.greenerself.sustainability.userlanding.model.UserAgreementVO;
import com.greenerself.sustainability.userlanding.repo.AgreementRepository;
import com.greenerself.sustainability.userlanding.repo.UserAgreementRepository;
import com.greenerself.sustainability.userlanding.repo.UserRepository;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserAgreementService {

    @Autowired
    private UserAgreementRepository userAgreementRepository;

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseVO recordAgreement(UserAgreementVO agreementVO) {
        ResponseVO response = new ResponseVO();

        //Validate the Input
        if (agreementVO == null || agreementVO.getUserId() == null || agreementVO.getAgreementType() == null) {
            response.setSuccess(false);
            response.addActionError("E003", "Invalid input data.");
            return response;
        }

        //Check User Existence
        User user = userRepository.findById(agreementVO.getUserId()).orElse(null);
        if (user == null) {
            response.setSuccess(false);
            response.addActionError("E004", "User not found.");
            return response;
        }

        //Check Agreement Type and Version
        Agreement currentAgreement = getCurrentAgreement(agreementVO.getAgreementType().toString());
        if (currentAgreement == null) {
            response.setSuccess(false);
            response.addActionError("E005", "Invalid agreement version.");
            return response;
        }

        //Record the Agreement
        UserAgreement userAgreement = new UserAgreement();
        userAgreement.setUser(user);
        userAgreement.setAgreement(currentAgreement);
        userAgreement.setAccepted(true);
        userAgreement.setAcceptedAt(new Timestamp(System.currentTimeMillis()));
        userAgreementRepository.save(userAgreement);

        // Step 5: Update User Status
        user.setActiveStatus('Y');  // Assuming 'Y' means active
        userRepository.save(user);

        // Step 6: Return a Response
        response.setData("Agreement recorded successfully.");
        return response;
    }

    public Agreement getCurrentAgreement(String type) {
        // Fetch the latest agreement by type, assuming the latest is determined by the highest version
        return agreementRepository.findTopByTypeOrderByVersionDesc(type);
//               todo .orElseThrow(() -> new ResourceNotFoundException("Agreement not found for type: " + type));
    }
}
