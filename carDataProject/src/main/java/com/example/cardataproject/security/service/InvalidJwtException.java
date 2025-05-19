package com.example.cardataproject.security.service;

import javax.naming.AuthenticationException;

public class InvalidJwtException extends AuthenticationException {

    public InvalidJwtException(String message) {
        super(message);
    }
}
