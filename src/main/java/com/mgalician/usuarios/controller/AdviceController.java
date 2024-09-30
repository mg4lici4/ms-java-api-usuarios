package com.mgalician.usuarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.model.dto.ErrorDto;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> throwGeneralException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorDto(
                        errorMap));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleInvalidArgument(
            MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> {
                            errorMap.put(
                                    error.getField(),
                                    error.getDefaultMessage());
                        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(
                        errorMap));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNorFound(
            ResourceNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(errorMap));
    }
}
