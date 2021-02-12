package com.example.demo.exception;

import static com.example.demo.exception.FunctionalErrorCode.WRONG_PASSWORD;

public class WrongPasswordException extends FunctionalException {



    public WrongPasswordException() {
        super(WRONG_PASSWORD);
    }
}
