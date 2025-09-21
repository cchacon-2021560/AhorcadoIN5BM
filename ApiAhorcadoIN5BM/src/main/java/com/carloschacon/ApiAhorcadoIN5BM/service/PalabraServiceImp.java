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

    public PalabraServiceImp(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
        this.validadorPalabra = new ValidadorPalabra(palabraRepository);
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Palabra savePalabra(Palabra palabra) {

        validadorPalabra.validarCampos(palabra);


        validadorPalabra.validarDuplicadoCrear(palabra.getNombre());


        return palabraRepository.save(palabra);
    }


    @Override
    public Palabra updatePalabra(Integer id, Palabra palabra) {
        Palabra existingPalabra = palabraRepository.findById(id).orElse(null);

        if (existingPalabra != null) {
            validadorPalabra.validarCampos(palabra);
            validadorPalabra.validarDuplicadoActualizar(palabra.getNombre(), id);

            existingPalabra.setNombre(palabra.getNombre());
            existingPalabra.setCualidadUno(palabra.getCualidadUno());
            existingPalabra.setCualidadDos(palabra.getCualidadDos());
            existingPalabra.setCualidadTres(palabra.getCualidadTres());
            return palabraRepository.save(existingPalabra);
        }
        return null;
    }

    @Override
    public void deletePalabra(Integer id) {
        palabraRepository.deleteById(id);
    }
}
