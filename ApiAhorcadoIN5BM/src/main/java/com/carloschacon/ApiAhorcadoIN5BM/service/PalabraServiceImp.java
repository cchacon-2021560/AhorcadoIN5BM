package com.carloschacon.ApiAhorcadoIN5BM.service;

import com.carloschacon.ApiAhorcadoIN5BM.repository.PalabraRepository;
import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import com.carloschacon.ApiAhorcadoIN5BM.controller.ValidadorPalabra;
import com.carloschacon.ApiAhorcadoIN5BM.service.PalabraInvalidaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServiceImp implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImp(PalabraRepository palabraRepository){
        this.palabraRepository = palabraRepository;
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
    public Palabra savePalabra(Palabra palabra) {
        ValidadorPalabra.validar(palabra);

        if (palabraRepository.findByNombreIgnoreCase(palabra.getNombre()).isPresent()) {
            throw new PalabraInvalidaException("La palabra '" + palabra.getNombre() + "' ya existe.");
        }

        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer id, Palabra palabra) {
        ValidadorPalabra.validar(palabra);

        Palabra existingPalabra = palabraRepository.findById(id).orElse(null);

        if (existingPalabra != null) {
            palabraRepository.findByNombreIgnoreCase(palabra.getNombre()).ifPresent(p -> {
                if (!p.getCodigoPalabra().equals(id)) {
                    throw new PalabraInvalidaException("La palabra '" + palabra.getNombre() + "' ya existe.");
                }
            });

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
