package com.example.demo.util;

import com.example.demo.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUser {
    public List<UserModel> findAllUsers();

    public Optional<UserModel> findUserById(int id);

    public UserModel findByUserName(String userName);

}

