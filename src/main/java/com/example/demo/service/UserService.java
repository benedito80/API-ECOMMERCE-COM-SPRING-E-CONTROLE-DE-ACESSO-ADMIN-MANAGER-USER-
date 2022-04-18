package com.example.demo.service;


import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserModel> findAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserModel> findUserById(int id) {
        return userRepo.findById(id);
    }

    public UserModel findByUserName(String userName) {
        UserModel user = userRepo.findByUserName(userName);
        return user;
    }

    public UserModel saveUser(UserModel newUser) {
        newUser.setPassword("{bcrypt}" + encoder.encode( newUser.getPassword()));
        UserModel user = userRepo.save(newUser);
        return user;
    }

    public UserModel updateUser(int id, UserModel user) {
        Optional<UserModel> retrievedUser = userRepo.findById(id);
        if (retrievedUser == null)
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        user.setPassword("{bcrypt}" + encoder.encode( user.getPassword()));
        userRepo.save(user);
        return userRepo.findById(id).get();
    }

    public UserModel deleteUser(int userId) {
        Optional<UserModel> retrievedUser = userRepo.findById(userId);
        if (retrievedUser == null)
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        userRepo.deleteById(userId);
        return retrievedUser.get();
    }
}








