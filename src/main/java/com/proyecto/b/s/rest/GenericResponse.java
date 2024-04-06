package com.proyecto.b.s.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record GenericResponse<T>(T data, HttpStatus httpStatus) {
    public static <T> ResponseEntity<GenericResponse<T>> createdResponse(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse<>(data, HttpStatus.CREATED));
    }

    public static <T> ResponseEntity<GenericResponse<T>> acceptedResponse() {
        return ResponseEntity.accepted().body(new GenericResponse<>(null, HttpStatus.ACCEPTED));
    }

    public static <T> ResponseEntity<GenericResponse<T>> acceptedResponse(T data) {
        return ResponseEntity.accepted().body(new GenericResponse<>(data, HttpStatus.ACCEPTED));
    }

    public static <T> ResponseEntity<GenericResponse<T>> okResponse(T data) {
        return ResponseEntity.ok(new GenericResponse<>(data, HttpStatus.OK));
    }

    public static <T> ResponseEntity<GenericResponse<T>> exceptionResponse(HttpStatus httpStatus) {
        return new ResponseEntity<>(new GenericResponse<>(null, httpStatus), httpStatus);
    }
}
