package com.kalptree.service;

import com.kalptree.entity.Users;
import com.kalptree.exception.AuthenticationException;
import com.kalptree.model.Login;
import com.kalptree.model.LoginResponse;
import com.kalptree.repository.UserRepository;
import com.kalptree.security.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public LoginResponse authenticateUser(Login login) throws AuthenticationException {
        LoginResponse loginResponse;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new AuthenticationException("BAD CREDENTIALS");
        }

        Optional<Users> optionalUser = userRepository.findByEmail(login.email());
        loginResponse = new LoginResponse(optionalUser.get().getUserId(), optionalUser.get().getEmail(), optionalUser.get().getRole(), tokenService.generateToken(authentication));

        return loginResponse;
    }
}
