package com.adi.myproject.service;

import com.adi.myproject.model.UserPrincipal;
import com.adi.myproject.model.Users;
import com.adi.myproject.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repo.findByUsername(username);

        if (users == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("404 Not Found");
        }

        return new UserPrincipal(users);
    }
}
