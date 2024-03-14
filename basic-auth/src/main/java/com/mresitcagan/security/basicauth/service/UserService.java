package com.mresitcagan.security.basicauth.service;

import com.mresitcagan.security.basicauth.dto.CreateUserRequest;
import com.mresitcagan.security.basicauth.model.User;
import com.mresitcagan.security.basicauth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByUsername(String username){
        return repository.findByUsername(username);
    }

    public User createUser(CreateUserRequest request){
        User newUser = User
                .builder()
                .name(request.name())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .authorities(request.authorities())
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .accountNonExpired(true)
                .build();
        return repository.save(newUser);
    }



}

