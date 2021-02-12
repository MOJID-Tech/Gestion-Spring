package com.example.demo.exception;
import static java.lang.String.format;
public class FunctionalException extends RuntimeException{

    private static final long serialVersionUID = 4273322132185545866L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public FunctionalErrorCode getErrorCode() {
        return errorCode;
    }

    private final FunctionalErrorCode errorCode;

    FunctionalException(String messageTemplate, FunctionalErrorCode errorCode, Throwable cause, String... arguments) {
        super(format(messageTemplate, arguments), cause);
        this.errorCode = errorCode;
    }

    FunctionalException(FunctionalErrorCode errorCode, String... arguments) {
        this(errorCode.getMessageTemplate(), errorCode, null, arguments);
    }
}
