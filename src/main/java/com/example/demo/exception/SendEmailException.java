package com.example.demo.exception;

import static com.example.demo.exception.FunctionalErrorCode.SEND_EMAIL;

public class SendEmailException extends FunctionalException {



    public SendEmailException() {
        super(SEND_EMAIL);
    }

}
