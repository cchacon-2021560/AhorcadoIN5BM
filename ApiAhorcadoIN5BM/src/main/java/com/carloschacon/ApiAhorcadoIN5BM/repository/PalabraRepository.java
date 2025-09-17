package com.carloschacon.ApiAhorcadoIN5BM.repository;

import com.carloschacon.ApiAhorcadoIN5BM.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
}