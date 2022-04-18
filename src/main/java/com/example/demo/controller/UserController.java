package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping
    public List<UserModel> getAllUsers(Authentication authentication) {
        return userService.findAllUsers();
    }

    @PostMapping()
    public ResponseEntity<UserModel> saveusers(@RequestBody UserModel newUser, Authentication auth) {
        //System.out.println(newUser.getUserName()+"  "+auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body((userService.saveUser(newUser)));
    }

    @PreAuthorize("@userSecurity.hasUserId(authentication,#userId)")
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("userId") Integer userId, Authentication authentication) {
        System.out.println("Inside getuserbyid method");
        return ResponseEntity.ok().body(userService.findUserById(userId).get());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("userId") Integer UserId, @RequestBody UserModel newUser) {
        return ResponseEntity.ok().body(userService.updateUser(UserId, newUser));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer UserId) {
        userService.deleteUser(UserId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> details(Authentication authentication, @RequestParam("valor") String param) {
        UserModel user = userService.findByUserName(param);
        return (user == null) ? ResponseEntity.badRequest().body("Usuário não localizado!") : ResponseEntity.ok().body(user);
    }
}

