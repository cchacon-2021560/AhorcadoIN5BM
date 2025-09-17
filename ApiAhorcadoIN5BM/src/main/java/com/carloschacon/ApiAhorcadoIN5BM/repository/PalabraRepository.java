package com.carloschacon.ApiAhorcadoIN5BM.repository;

import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
    Optional<Palabra> findByNombreIgnoreCase(String nombre);
}
