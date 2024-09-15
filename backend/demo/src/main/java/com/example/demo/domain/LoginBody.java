package com.example.demo.domain;

public class LoginBody {

    private UserType type;

    LoginBody() {};

    LoginBody(UserType type) {
        this.type = type;
    }

    public UserType getType() {
        return this.type;
    }
}
