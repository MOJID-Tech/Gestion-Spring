package com.example.demo.exception;

import static com.example.demo.exception.FunctionalErrorCode.NOT_FOUND_ENTITY;
import static com.example.demo.exception.FunctionalErrorCode.NOT_FOUND_ENTITY_BY_FIELD;
import static java.lang.String.valueOf;

public class NotFoundException extends FunctionalException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(Class<?> entityClass, Long id) {
        super(NOT_FOUND_ENTITY, entityClass.getSimpleName(), valueOf(id));
    }
    public NotFoundException(Class<?> entityClass, Integer id) {
        super(NOT_FOUND_ENTITY, entityClass.getSimpleName(), valueOf(id));
    }


    public NotFoundException(Class<?> entityClass, String field, String value) {
        super(NOT_FOUND_ENTITY_BY_FIELD, entityClass.getSimpleName(), field, value);
    }

}
