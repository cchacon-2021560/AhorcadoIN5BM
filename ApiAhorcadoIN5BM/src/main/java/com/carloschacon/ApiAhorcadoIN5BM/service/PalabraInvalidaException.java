package com.carloschacon.ApiAhorcadoIN5BM.service;

public class PalabraInvalidaException extends RuntimeException {
    public PalabraInvalidaException(String mensaje) {
        super(mensaje);
    }
}
