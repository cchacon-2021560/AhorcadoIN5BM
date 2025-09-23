package com.carloschacon.ApiAhorcadoIN5BM.controller;

import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import com.carloschacon.ApiAhorcadoIN5BM.repository.PalabraRepository;
import com.carloschacon.ApiAhorcadoIN5BM.service.PalabraInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPalabra {

    private final PalabraRepository palabraRepository;

    public ValidadorPalabra(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    public void validarCampos(Palabra palabra) {
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

    public boolean existeDuplicado(String nombre) {
        return palabraRepository.findByNombreIgnoreCase(nombre.trim()).isPresent();
    }


    public void validarDuplicadoActualizar(String nombre, Integer id) {
        palabraRepository.findByNombreIgnoreCase(nombre.trim())
                .ifPresent(p -> {
                    if (!p.getCodigoPalabra().equals(id)) {
                        throw new PalabraInvalidaException("La palabra ya existe.");
                    }
                });
    }
}
