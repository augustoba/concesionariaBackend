package com.proyecto.b.s.rest;import com.proyecto.b.s.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoExistDBException(ResourceNotFoundException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(ResourceAlreadyExistsException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(InvalidResourceException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleInvalidResourceException(InvalidResourceException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(ResourceEntityNullException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleResourceEntityNullException(ResourceEntityNullException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(SearchStateException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleSearchStateException(SearchStateException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(PersonStateException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handlePersonStateException(PersonStateException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Map<String, List<String>>>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult objBindingResult = e.getBindingResult();
        Map<String, Map<String, List<String>>> errorMap = new HashMap<>();
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError error : objBindingResult.getFieldErrors()) {
            String key = error.getField();
            String value = error.getDefaultMessage();
            List<String> values = new ArrayList<>();

            if (errors.containsKey(key)) {
                values = errors.get(key);
            }
            values.add(value);
            errors.put(key, values);
        }
        errorMap.put("Errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

}
