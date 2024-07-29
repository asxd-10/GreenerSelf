package com.greenerself.sustainability.userlanding.controller;

import com.greenerself.sustainability.userlanding.model.UserAgreementVO;
import com.greenerself.sustainability.userlanding.service.UserAgreementService;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-agreements")
public class UserAgreementController {

    @Autowired
    private UserAgreementService userAgreementService;

    @PostMapping("/agreement")
    public ResponseEntity<ResponseVO> handleUserAgreement(@RequestBody UserAgreementVO agreement) {
        if (agreement.getAgreed()) {
            // Record the agreement and activate the user account
            ResponseVO response = userAgreementService.recordAgreement(agreement);
            return ResponseEntity.ok(response);
        } else {
            // Handle the case where the user does not agree
            ResponseVO response = new ResponseVO();
            response.setSuccess(false);
            response.addActionError("E002", "You must agree to the terms and conditions to complete registration to GreenerSelf");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
