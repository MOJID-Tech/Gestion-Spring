package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;

import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository users) {
        this.userRepository = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return userRepository.findByLogin(username).orElseThrow(()
                -> new NotFoundException(User.class, "login", username));
    }
}
