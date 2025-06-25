package com.co.FinanzasFamily.controller;

import com.co.FinanzasFamily.model.ResumenMensual;
import com.co.FinanzasFamily.service.ResumenMensualService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resumen-mensual")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://finanzas-family-front.vercel.app"
})
public class ResumenMensualController {

    private final ResumenMensualService service;

    public ResumenMensualController(ResumenMensualService service) {
        this.service = service;
    }

    @GetMapping
    public Optional<ResumenMensual> getResumen(@RequestParam int mes,
                                               @RequestParam int anio) {
        return service.obtenerResumen(mes, anio);
    }

    @PostMapping("/cerrar")
    public ResumenMensual cerrarMes(@RequestParam int mes,
                                    @RequestParam int anio,
                                    @RequestParam boolean completo) {
        return service.cerrarMes(mes, anio, completo);
    }

    @DeleteMapping
    public void eliminarCierre(@RequestParam int mes, @RequestParam int anio) {
        service.eliminarCierre(mes, anio);
    }

    @GetMapping("/historial")
    public List<ResumenMensual> listarTodos() {
        return service.listarTodos();
    }

}
