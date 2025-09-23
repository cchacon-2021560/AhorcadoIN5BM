package com.carloschacon.ApiAhorcadoIN5BM.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PalabraInvalidaException extends RuntimeException {
    public PalabraInvalidaException(String message) {
        super(message);
    }
}
