package com.adi.myproject.controller;

import com.adi.myproject.model.Users;
import com.adi.myproject.service.JwtService;
import com.adi.myproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersService service;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/api/register")
    public Users addNewUser(@RequestBody Users newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return service.addUser(newUser);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody Users user) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "User access denied";
        }
    }
}
