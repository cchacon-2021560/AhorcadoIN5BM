package com.carloschacon.ApiAhorcadoIN5BM.controller;

import com.carloschacon.ApiAhorcadoIN5BM.model.Usuario;
import com.carloschacon.ApiAhorcadoIN5BM.repository.UsuarioRepository;
import com.carloschacon.ApiAhorcadoIN5BM.service.UsuarioInvalidoException;

public class ValidadorUsuario {

    private final UsuarioRepository usuarioRepository;

    public ValidadorUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validarDuplicado(String correo) {
        boolean existe = usuarioRepository.findAll().stream()
                .anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo));
        if (existe) {
            throw new UsuarioInvalidoException("El correo ya está registrado.");
        }
    }

    public void validarDuplicado(String correo, Integer id) {
        boolean existe = usuarioRepository.findAll().stream()
                .anyMatch(u ->
                        u.getCorreo().equalsIgnoreCase(correo)
                                && !u.getCodigoUsuario().equals(id)
                );
        if (existe) {
            throw new UsuarioInvalidoException("El correo ya está registrado.");
        }
    }

    public void validarFormatoYNoVacio(Usuario usuario) {
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new UsuarioInvalidoException("El correo no puede estar vacío.");
        }

        if (!usuario.getCorreo().matches("^[A-Za-z0-9+_.-]+@(gmail|yahoo|edu|org)\\.[a-z]{2,6}$")) {
            throw new UsuarioInvalidoException("El correo no tiene un dominio válido o no está completo.");
        }

        if (usuario.getContra() == null || usuario.getContra().isBlank()) {
            throw new UsuarioInvalidoException("La contraseña no puede estar vacía.");
        }
    }
}
