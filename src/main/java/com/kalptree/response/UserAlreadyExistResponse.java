package com.kalptree.response;

import org.springframework.stereotype.Component;

@Component
public class UserAlreadyExistResponse {
    String message = "USER ALREADY EXIST";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
