package com.miguelangel.taqueria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miguelangel.taqueria.entities.Categoria;
import com.miguelangel.taqueria.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        Categoria categoria = categoriaService
                .buscarPorId(id)
                .orElse(null);

        model.addAttribute("categoria", categoria);

        return "categorias/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}