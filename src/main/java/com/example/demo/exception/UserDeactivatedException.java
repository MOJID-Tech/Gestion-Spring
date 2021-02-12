package com.example.demo.exception;

import static com.example.demo.exception.FunctionalErrorCode.USER_DEACTIVATED;

public class UserDeactivatedException extends FunctionalException {



    public UserDeactivatedException() {
        super(USER_DEACTIVATED);
    }

}
