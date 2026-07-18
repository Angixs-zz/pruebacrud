package com.miguelangel.taqueria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguelangel.taqueria.entities.Taco;
import com.miguelangel.taqueria.repositories.TacoRepository;

@Service
public class TacoService {

    private final TacoRepository tacoRepository;

    public TacoService(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    public List<Taco> listarTodos() {
        return tacoRepository.findAll();
    }

    public Optional<Taco> buscarPorId(Integer id) {
        return tacoRepository.findById(id);
    }

    public Taco guardar(Taco taco) {
        return tacoRepository.save(taco);
    }

    public boolean eliminar(Integer id) {

        if (!tacoRepository.existsById(id)) {
            return false;
        }

        tacoRepository.deleteById(id);
        return true;
    }
}