package com.carloschacon.ApiAhorcadoIN5BM.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
    Optional<Palabra> findByNombreIgnoreCase(String nombre);
}

