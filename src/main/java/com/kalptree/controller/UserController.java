package com.kalptree.controller;

import com.kalptree.entity.Users;
import com.kalptree.exception.UserAlreadyExistException;
import com.kalptree.response.ResponseHandler;
import com.kalptree.response.UserAlreadyExistResponse;
import com.kalptree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAlreadyExistResponse userAlreadyExistResponse;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users user) {
        Users newUser;
        try {
            newUser = userService.addUser(user);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistResponse);
        }
        return new ResponseEntity<>(ResponseHandler.generateResponse("Successfully added user", HttpStatus.CREATED, newUser), HttpStatus.CREATED);
    }

}
