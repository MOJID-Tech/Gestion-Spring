package com.example.demo.exception;



import static com.example.demo.exception.FunctionalErrorCode.EMAIL_NOT_FOUND;

public class EmailNotFoundException extends FunctionalException {



    public EmailNotFoundException() {
        super(EMAIL_NOT_FOUND);
    }
}
