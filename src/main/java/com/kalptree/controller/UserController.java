package com.kalptree.controller;

import com.kalptree.entity.Users;
import com.kalptree.exception.UserAlreadyExistException;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kalptree.response.ResponseMessageConstants.successUserAdded;
import static com.kalptree.response.ResponseMessageConstants.userAlreadyExist;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users user) {
        Users newUser;
        try {
            newUser = userService.addUser(user);
        } catch (UserAlreadyExistException e) {
            return ResponseHandler.generateResponse(userAlreadyExist, HttpStatus.CONFLICT, null);
        }
        return ResponseHandler.generateResponse(successUserAdded, HttpStatus.CREATED, newUser);
    }

}
