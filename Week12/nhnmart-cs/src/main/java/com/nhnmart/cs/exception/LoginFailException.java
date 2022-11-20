package com.nhnmart.cs.exception;

public class LoginFailException extends RuntimeException {

    public LoginFailException(String id) {
        super("Login Failed : " + id);
    }
}
