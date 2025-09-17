package com.carloschacon.ApiAhorcadoIN5BM.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.carloschacon.ApiAhorcadoIN5BM.service.PalabraInvalidaException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidadorController {

    @ExceptionHandler(PalabraInvalidaException.class)
    public ResponseEntity<Object> handlePalabraInvalidaException(PalabraInvalidaException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
