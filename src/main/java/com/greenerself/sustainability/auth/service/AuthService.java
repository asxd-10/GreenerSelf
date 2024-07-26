package com.greenerself.sustainability.auth.service;

import com.greenerself.sustainability.auth.util.JwtUtil;
import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.userlanding.service.UserService;
import com.greenerself.sustainability.util.vo.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public void register(UserRegistrationRequest userRegistrationRequest) {
        userService.save(userRegistrationRequest);
    }
}
