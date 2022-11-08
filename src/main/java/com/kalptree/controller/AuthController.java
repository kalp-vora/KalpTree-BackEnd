package com.kalptree.controller;

import com.kalptree.exception.AuthenticationException;
import com.kalptree.model.Login;
import com.kalptree.model.LoginResponse;
import com.kalptree.response.ResponseHandler;
import com.kalptree.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kalptree.response.ResponseMessageConstants.badCredentials;
import static com.kalptree.response.ResponseMessageConstants.successAuthenticate;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        LoginResponse loginResponse;
        try {
            loginResponse = authService.authenticateUser(login);
        } catch (AuthenticationException e) {
            return ResponseHandler.generateResponse(badCredentials, HttpStatus.UNAUTHORIZED, null);
        }
        return ResponseHandler.generateResponse(successAuthenticate, HttpStatus.OK, loginResponse);

    }

}