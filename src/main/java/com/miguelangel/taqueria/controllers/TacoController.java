package com.miguelangel.taqueria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miguelangel.taqueria.entities.Taco;
import com.miguelangel.taqueria.services.CategoriaService;
import com.miguelangel.taqueria.services.TacoService;

@Controller
@RequestMapping("/tacos")
public class TacoController {

    private final TacoService tacoService;
    private final CategoriaService categoriaService;

    public TacoController(
            TacoService tacoService,
            CategoriaService categoriaService) {

        this.tacoService = tacoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tacos", tacoService.listarTodos());
        return "tacos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("taco", new Taco());
        model.addAttribute(
                "categorias",
                categoriaService.listarTodas()
        );

        return "tacos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(Taco taco) {
        tacoService.guardar(taco);
        return "redirect:/tacos";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model) {

        Taco taco = tacoService
                .buscarPorId(id)
                .orElse(null);

        model.addAttribute("taco", taco);
        model.addAttribute(
                "categorias",
                categoriaService.listarTodas()
        );

        return "tacos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        tacoService.eliminar(id);
        return "redirect:/tacos";
    }
}