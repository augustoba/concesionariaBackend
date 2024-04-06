package com.proyecto.b.s.exception;

import org.springframework.http.HttpStatus;

public class PersonStateException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public PersonStateException(String code) {
        this.code = code;
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return getMessageForCode(code);
    }

    private String getMessageForCode(String code) {
        return switch (code) {
            case "contratada" -> personContratada();
            case "noRecomendable" -> personNoRecomendable();
            default -> "Error desconocido";
        };
    }

    private String personContratada() {
        return "La persona ya se encuentra contratada.";
    }

    private String personNoRecomendable() {
        return "La persona es NO RECOMENDABLE.";
    }
}

