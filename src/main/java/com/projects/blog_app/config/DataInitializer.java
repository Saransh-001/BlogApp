package com.projects.blog_app.config;

import com.projects.blog_app.model.Roles;
import com.projects.blog_app.model.Users;
import com.projects.blog_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(userRepo.findByUsername("admin").isEmpty()){
            Users adminUser = new Users();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRoles(Collections.singleton(Roles.ADMIN));
            userRepo.save(adminUser);
        }

    }

}
