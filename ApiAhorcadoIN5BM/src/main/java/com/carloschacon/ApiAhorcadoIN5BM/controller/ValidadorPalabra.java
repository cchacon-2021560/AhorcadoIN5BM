package com.carloschacon.ApiAhorcadoIN5BM.controller;

import com.carloschacon.ApiAhorcadoIN5BM.service.PalabraInvalidaException;
import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;

public class ValidadorPalabra {

    public static void validar(Palabra palabra) {
        if (palabra.getNombre() == null || palabra.getNombre().trim().isEmpty()) {
            throw new PalabraInvalidaException("El nombre de la palabra no puede estar vacío.");
        }

        if (!palabra.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
            throw new PalabraInvalidaException("El nombre de la palabra solo debe contener letras.");
        }

        if (palabra.getNombre().length() < 3) {
            throw new PalabraInvalidaException("El nombre de la palabra debe tener al menos 3 letras.");
        }

        if (palabra.getCualidadUno() == null || palabra.getCualidadUno().trim().isEmpty()) {
            throw new PalabraInvalidaException("La cualidad uno no puede estar vacía.");
        }
        if (palabra.getCualidadDos() == null || palabra.getCualidadDos().trim().isEmpty()) {
            throw new PalabraInvalidaException("La cualidad dos no puede estar vacía.");
        }
        if (palabra.getCualidadTres() == null || palabra.getCualidadTres().trim().isEmpty()) {
            throw new PalabraInvalidaException("La cualidad tres no puede estar vacía.");
        }
    }
}
