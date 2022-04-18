package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    UserRepository userRepo;

    public boolean hasUserId(Authentication authentication, Integer userId) {
        int userID = userRepo.findByUserName(authentication.getName()).getId();
        if (userID == userId) return true;
        return false;
    }
}

