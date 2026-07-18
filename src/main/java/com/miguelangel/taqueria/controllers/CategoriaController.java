package com.miguelangel.taqueria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String eliminar(
            @PathVariable Integer id,
            RedirectAttributes atributos) {

        boolean eliminada = categoriaService.eliminar(id);

        if (eliminada) {
            atributos.addFlashAttribute(
                    "mensajeExito",
                    "La categoria se elimino correctamente"
            );
        } else {
            atributos.addFlashAttribute(
                    "mensajeError",
                    "No se puede eliminar la categoria porque no existe o tiene tacos asociados"
            );
        }

        return "redirect:/categorias";
    }
}