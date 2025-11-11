package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String rawPassword, Set<String> roles) {
        if (userRepo.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        User u = new User(username, passwordEncoder.encode(rawPassword), roles);
        return userRepo.save(u);
    }
}
