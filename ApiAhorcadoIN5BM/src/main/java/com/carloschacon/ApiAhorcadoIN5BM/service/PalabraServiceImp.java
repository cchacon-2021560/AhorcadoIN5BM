package com.carloschacon.ApiAhorcadoIN5BM.service;

import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import com.carloschacon.ApiAhorcadoIN5BM.repository.PalabraRepository;
import com.carloschacon.ApiAhorcadoIN5BM.controller.ValidadorPalabra;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PalabraServiceImp implements PalabraService {

    private final PalabraRepository palabraRepository;
    private final ValidadorPalabra validadorPalabra;

    public PalabraServiceImp(PalabraRepository palabraRepository, ValidadorPalabra validadorPalabra) {
        this.palabraRepository = palabraRepository;
        this.validadorPalabra = validadorPalabra;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id)
                .orElseThrow(() -> new PalabraInvalidaException("Palabra no encontrada con id " + id));
    }

    @Override
    @Transactional
    public Palabra savePalabra(Palabra palabra) {
        validadorPalabra.validarCampos(palabra);
        if (validadorPalabra.existeDuplicado(palabra.getNombre())) {
            throw new PalabraInvalidaException("La palabra '" + palabra.getNombre().trim() + "' ya existe.");
        }
        return palabraRepository.save(palabra);
    }


    @Override
    public Palabra updatePalabra(Integer id, Palabra palabra) {
        Palabra existingPalabra = palabraRepository.findById(id)
                .orElseThrow(() -> new PalabraInvalidaException("Palabra no encontrada con id " + id));

            validadorPalabra.validarCampos(palabra);
            validadorPalabra.validarDuplicadoActualizar(palabra.getNombre(), id);

            existingPalabra.setNombre(palabra.getNombre());
            existingPalabra.setCualidadUno(palabra.getCualidadUno());
            existingPalabra.setCualidadDos(palabra.getCualidadDos());
            existingPalabra.setCualidadTres(palabra.getCualidadTres());
            return palabraRepository.save(existingPalabra);
    }

    @Override
    public void deletePalabra(Integer id) {
        if (!palabraRepository.existsById(id)) {
            throw new PalabraInvalidaException("No se puede eliminar. Palabra no encontrada con id " + id);
        }
        palabraRepository.deleteById(id);
    }
}
