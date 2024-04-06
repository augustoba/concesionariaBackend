package com.proyecto.b.s.exception;

import org.springframework.http.HttpStatus;

public class InvalidResourceException extends IllegalArgumentException{
    private final String field;
    private final HttpStatus httpStatus;
    private final String message;

    public InvalidResourceException(String field) {
        this.field = field;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = field + " ya se encuentra asignado.";
    }

    public String getField() {
        return field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
