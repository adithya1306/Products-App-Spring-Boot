package com.adi.myproject.service;

import com.adi.myproject.model.Users;
import com.adi.myproject.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepo repo;

    public Users addUser(Users newUser) {
        return repo.save(newUser);
    }
}
