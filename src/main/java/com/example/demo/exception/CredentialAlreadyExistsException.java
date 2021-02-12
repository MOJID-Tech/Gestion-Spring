package com.example.demo.exception;

import static  com.example.demo.exception.FunctionalErrorCode.CREDENTIAL_ALREADY_USED;

public class CredentialAlreadyExistsException extends FunctionalException {



    public CredentialAlreadyExistsException(final String property) {
        super(CREDENTIAL_ALREADY_USED, property);
    }

}
