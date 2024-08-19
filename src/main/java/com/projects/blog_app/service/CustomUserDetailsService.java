package com.projects.blog_app.service;

import com.projects.blog_app.config.CustomUserDetails;
import com.projects.blog_app.model.Users;
import com.projects.blog_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepo.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(userOptional.get());
    }

}
