package com.kalptree.response;

import org.springframework.stereotype.Component;

@Component
public class LoginFailedResponse {
    String message = "BAD CREDENTIALS";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
