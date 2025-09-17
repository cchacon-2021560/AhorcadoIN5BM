package com.carloschacon.ApiAhorcadoIN5BM.controller;

import com.carloschacon.ApiAhorcadoIN5BM.service.UsuarioInvalidoException;
import com.carloschacon.ApiAhorcadoIN5BM.model.Usuario;
import com.carloschacon.ApiAhorcadoIN5BM.repository.UsuarioRepository;

import java.util.regex.Pattern;

public class ValidadorUsuario {

    private final UsuarioRepository usuarioRepository;

    public ValidadorUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validar(Usuario usuario) {
        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
            throw new UsuarioInvalidoException("El correo no puede estar vacío.");
        }

        if (usuario.getContra() == null || usuario.getContra().trim().isEmpty()) {
            throw new UsuarioInvalidoException("La contraseña no puede estar vacía.");
        }

        String regexCorreo = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(regexCorreo, usuario.getCorreo())) {
            throw new UsuarioInvalidoException("El formato del correo no es válido.");
        }

        String correo = usuario.getCorreo().toLowerCase();
        if (!(correo.endsWith("@gmail.com") ||
                correo.endsWith("@yahoo.com") ||
                correo.endsWith(".edu") ||
                correo.endsWith("@outlook.com") ||
                correo.endsWith(".org"))) {
            throw new UsuarioInvalidoException("El dominio del correo no está permitido. Solo se aceptan: gmail, yahoo, edu, org.");
        }

        boolean existeCorreo = usuarioRepository.findAll().stream()
                .anyMatch(u -> u.getCorreo().equalsIgnoreCase(usuario.getCorreo()));
        if (existeCorreo) {
            throw new UsuarioInvalidoException("El correo ya está registrado.");
        }
    }
}
