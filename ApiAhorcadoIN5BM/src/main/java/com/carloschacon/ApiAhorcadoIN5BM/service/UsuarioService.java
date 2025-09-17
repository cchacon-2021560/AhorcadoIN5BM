package com.carloschacon.ApiAhorcadoIN5BM.service;

import com.carloschacon.ApiAhorcadoIN5BM.model.Usuario;

import java.util.List;


public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);
}
