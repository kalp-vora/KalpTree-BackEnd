package com.kalptree.controller;

import com.kalptree.entity.Users;
import com.kalptree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody Users user) {
        userService.addUser(user);
    }
}
