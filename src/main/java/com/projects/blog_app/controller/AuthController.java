package com.projects.blog_app.controller;

import com.projects.blog_app.model.Roles;
import com.projects.blog_app.model.Users;
import com.projects.blog_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;

@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton((Roles.USER)));
        userRepo.save(user);
        return "User registered successfully";
    }

    @GetMapping("/login")
    public String login(){
        return "Log in";
    }

}
