package com.kalptree.controller;

import com.kalptree.model.Login;
import com.kalptree.response.ResponseHandler;
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

import static com.kalptree.response.ResponseMessageConstants.badCredentials;
import static com.kalptree.response.ResponseMessageConstants.successAuthenticate;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(ResponseHandler.generateResponse(badCredentials, HttpStatus.NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ResponseHandler.generateResponse(successAuthenticate, HttpStatus.OK, tokenService.generateToken(authentication)), HttpStatus.OK);
    }
}