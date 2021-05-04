package com.fooddeliveryservice.app.detailservices;

import com.fooddeliveryservice.app.entities.Users;
import com.fooddeliveryservice.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = repository.findUsersByUsername(s);
        if (user != null){
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found!");
        }

    }
}
