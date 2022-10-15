package com.kalptree.controller;

import com.kalptree.model.Login;
import com.kalptree.response.ResponseHandler;
import com.kalptree.response.LoginFailedResponse;
import com.kalptree.security.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final LoginFailedResponse loginFailedResponse;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(LoginFailedResponse loginFailedResponse, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.loginFailedResponse = loginFailedResponse;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginFailedResponse);
        }
        return new ResponseEntity<>(
                ResponseHandler.generateResponse("Successfully Authenticated",
                        HttpStatus.OK, tokenService.generateToken(authentication)),
                HttpStatus.OK);
    }
}