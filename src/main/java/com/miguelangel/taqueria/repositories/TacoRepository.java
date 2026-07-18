package com.miguelangel.taqueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguelangel.taqueria.entities.Taco;

public interface TacoRepository extends JpaRepository<Taco, Integer> {

    boolean existsByCategoriaId(Integer categoriaId);
}