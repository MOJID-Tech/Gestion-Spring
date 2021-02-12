package com.example.demo.exception;
import com.example.demo.exception.FunctionalException;

import static com.example.demo.exception.FunctionalErrorCode.INSUFFICIENT_RIGHT;

public class InsufficientRightException extends FunctionalException {



    public InsufficientRightException() {
        super(INSUFFICIENT_RIGHT);
    }

}
