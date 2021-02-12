package com.example.demo.exception;

import java.util.List;

import static com.example.demo.exception.FunctionalErrorCode.NOT_NULL_FIELDS;
import static java.lang.String.join;

public class MontantDepasseException extends FunctionalException {


    public MontantDepasseException(final String field) {
        super(NOT_NULL_FIELDS, field);
    }

    public MontantDepasseException(final List<String> fields) {
        super(NOT_NULL_FIELDS, join(", ", fields));
    }

}
