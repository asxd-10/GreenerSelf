package com.greenerself.sustainability.userlanding.service;

import com.greenerself.sustainability.userlanding.entity.User;
import com.greenerself.sustainability.userlanding.repo.UserRepository;
import com.greenerself.sustainability.util.vo.CustomUserDetails;
import com.greenerself.sustainability.util.vo.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.greenerself.sustainability.auth.util.ValidationUtil.*;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public void save(UserRegistrationRequest userRegistrationRequest) {

        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (!isValidEmail(userRegistrationRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Validate contact number
        if (!isValidContactNumber(userRegistrationRequest.getContactNumber())) {
            throw new IllegalArgumentException("Invalid contact number");
        }

        // Validate role
        if (!isValidRole(userRegistrationRequest.getRole())) {
            throw new IllegalArgumentException("Invalid role");
        }

        if (!isValidPassword(userRegistrationRequest.getPassword(), userRegistrationRequest.getUsername())) {
            throw new IllegalArgumentException("Invalid password. Passwrod must contain Minimum eight and maximum 25 characters, at least one uppercase letter, one lowercase letter, one number and one special character \n Also it must not contain your username & 'password'");
        }


        User user = new User();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setActiveStatus('Y');
        user.setEmail(userRegistrationRequest.getEmail());
        user.setRole(userRegistrationRequest.getRole());
        user.setContactNumber(userRegistrationRequest.getContactNumber());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new CustomUserDetails(user);
    }
}
