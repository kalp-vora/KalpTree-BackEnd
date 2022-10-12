package com.kalptree.service;

import com.kalptree.entity.Users;
import com.kalptree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users addUser(Users user) {

        // ENCRYPTING PASSWORD BEFORE SAVING
        passwordEncoder.encode(user.getPassword());
        return userRepository.save(user);

    }
}
