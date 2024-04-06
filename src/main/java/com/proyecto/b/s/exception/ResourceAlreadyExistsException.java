package com.proyecto.b.s.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends RuntimeException {

    private final String code;
    private final String field;
    private HttpStatus httpStatus;

    public ResourceAlreadyExistsException(String code, String field) {
        this.code = code;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        String message = "";

        if (code.equals("existDB")) {
            message = existDB();
        }
        if (code.equals("existDBStatus")) {
            message = existDBStatus();
        }

        return message;
    }

    private String existDB() {
        httpStatus = HttpStatus.CONFLICT;

        return "El recurso '" + field + "' que desea crear ya existe.";
    }

    private String existDBStatus() {
        httpStatus = HttpStatus.GONE;

        return "El recurso '" + field + "' que desea crear ya existe, pero está dado de baja.";
    }
}
