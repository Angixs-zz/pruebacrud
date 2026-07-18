package com.miguelangel.taqueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguelangel.taqueria.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}