package com.example.demo.exception;


import static com.example.demo.exception.FunctionalErrorCode.INVALID_JWT_TOKEN;

public class InvalidJwtAuthenticationException extends FunctionalException {

    public InvalidJwtAuthenticationException() {
        super(INVALID_JWT_TOKEN);
    }

}
