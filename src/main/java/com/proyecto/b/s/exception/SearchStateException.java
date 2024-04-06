package com.proyecto.b.s.exception;

import org.springframework.http.HttpStatus;

public class SearchStateException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public SearchStateException(String code) {
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
            case "concluida" -> searchConcluida();
            case "cupoLleno" -> searchCupoLleno();
            default -> "Error desconocido";
        };
    }

    private String searchConcluida() {
        return "La búsqueda está concluída.";
    }

    private String searchCupoLleno() {
        return "La búsqueda cumplió su cupo de vacantes.";
    }
}
