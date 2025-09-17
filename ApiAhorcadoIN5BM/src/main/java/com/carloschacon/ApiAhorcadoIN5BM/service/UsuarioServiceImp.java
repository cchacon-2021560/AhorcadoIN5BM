package com.carloschacon.ApiAhorcadoIN5BM.service;

import com.carloschacon.ApiAhorcadoIN5BM.repository.UsuarioRepository;
import com.carloschacon.ApiAhorcadoIN5BM.model.Usuario;
import com.carloschacon.ApiAhorcadoIN5BM.controller.ValidadorUsuario;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        ValidadorUsuario validator = new ValidadorUsuario(usuarioRepository);
        validator.validar(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);

        if (existingUsuario != null) {
            ValidadorUsuario validator = new ValidadorUsuario(usuarioRepository);
            validator.validar(usuario);

            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setContra(usuario.getContra());
            return usuarioRepository.save(existingUsuario);
        }

        return null;
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
