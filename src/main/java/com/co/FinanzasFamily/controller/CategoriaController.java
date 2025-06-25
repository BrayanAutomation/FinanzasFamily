package com.co.FinanzasFamily.controller;

import com.co.FinanzasFamily.model.Categoria;
import com.co.FinanzasFamily.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "finanzas-family-front.vercel.app"
}) // para permitir acceso desde el frontend React
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }
}
