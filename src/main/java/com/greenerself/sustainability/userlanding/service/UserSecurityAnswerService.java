package com.greenerself.sustainability.userlanding.service;

import com.greenerself.sustainability.userlanding.entity.SecurityQuestion;
import com.greenerself.sustainability.userlanding.entity.UserSecurityAnswer;
import com.greenerself.sustainability.userlanding.repo.SecurityQuestionRepository;
import com.greenerself.sustainability.userlanding.repo.UserSecurityAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSecurityAnswerService {

    @Autowired
    private UserSecurityAnswerRepository userSecurityAnswerRepository;

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;

    public List<SecurityQuestion> getAllSecurityQuestions() {
        return securityQuestionRepository.findAll();
    }
    public void saveUserSecurityAnswers(List<UserSecurityAnswer> answers) {
        userSecurityAnswerRepository.saveAll(answers);
    }
}
