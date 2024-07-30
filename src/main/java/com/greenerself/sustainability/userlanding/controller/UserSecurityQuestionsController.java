package com.greenerself.sustainability.userlanding.controller;


import com.greenerself.sustainability.userlanding.entity.UserSecurityAnswer;
import com.greenerself.sustainability.userlanding.service.UserSecurityAnswerService;
import com.greenerself.sustainability.util.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-security-questions")
public class UserSecurityQuestionsController {
    @Autowired
    private UserSecurityAnswerService userSecurityAnswerService;

//    @PostMapping
//    public ResponseEntity<ResponseVO> saveUserSecurityAnswers(@RequestBody List<UserSecurityAnswer> answers) {
//        userSecurityAnswerService.saveUserSecurityAnswers(answers);
//        ResponseVO response = new ResponseVO(null, List.of("Security answers saved successfully"), null, true);
//        return ResponseEntity.ok(response);
//    }
}
