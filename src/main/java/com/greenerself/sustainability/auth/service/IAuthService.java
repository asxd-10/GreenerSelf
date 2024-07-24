package com.greenerself.sustainability.auth.service;

import com.greenerself.sustainability.userlanding.entity.User;

public interface IAuthService {
    String login(String username, String password);

    void register(User user);
}
