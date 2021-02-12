package com.example.demo.exception;

import static com.example.demo.exception.FunctionalErrorCode.PROPERTY_ALREADY_USED;

public class PropertyAlreadyUsedException extends FunctionalException {



    public PropertyAlreadyUsedException(final String property) {
        super(PROPERTY_ALREADY_USED, property);
    }

}

