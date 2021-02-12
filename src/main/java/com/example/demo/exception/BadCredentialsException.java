package com.example.demo.exception;


import static com.example.demo.exception.FunctionalErrorCode.BAD_CREDENTIALS;

public class BadCredentialsException extends FunctionalException {

    public BadCredentialsException() {
        super(BAD_CREDENTIALS);
    }

}
