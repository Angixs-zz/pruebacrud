package com.miguelangel.taqueria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguelangel.taqueria.entities.Categoria;
import com.miguelangel.taqueria.repositories.CategoriaRepository;
import com.miguelangel.taqueria.repositories.TacoRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final TacoRepository tacoRepository;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            TacoRepository tacoRepository) {

        this.categoriaRepository = categoriaRepository;
        this.tacoRepository = tacoRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public boolean eliminar(Integer id) {

        if (!categoriaRepository.existsById(id)) {
            return false;
        }

        if (tacoRepository.existsByCategoriaId(id)) {
            return false;
        }

        categoriaRepository.deleteById(id);
        return true;
    }
}