package com.project.shopease.services;

import com.project.shopease.config.JwtService;
import com.project.shopease.models.Users;
import com.project.shopease.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public String register(Users user){
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already Registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("user");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepo.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(String email,String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        UserDetails user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
         return jwtService.generateToken(user);
    }




}
