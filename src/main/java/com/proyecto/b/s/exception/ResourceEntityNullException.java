package com.proyecto.b.s.exception;

import org.springframework.http.HttpStatus;

public class ResourceEntityNullException extends NullPointerException {
    private final String field;
    private final HttpStatus httpStatus;

    public ResourceEntityNullException(String field) {
        super("El recurso '" + field + "' que ingresó es nulo");
        this.field = field;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public String getField() {
        return field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
