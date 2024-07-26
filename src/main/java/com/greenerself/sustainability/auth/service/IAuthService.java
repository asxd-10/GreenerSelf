package com.greenerself.sustainability.auth.service;

import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.util.vo.UserRegistrationRequest;

public interface IAuthService {
    String login(String username, String password);

    void register(UserRegistrationRequest userRegistrationRequest);
}
