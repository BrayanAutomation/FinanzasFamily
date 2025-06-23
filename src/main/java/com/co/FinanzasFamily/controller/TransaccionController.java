package com.co.FinanzasFamily.controller;

import com.co.FinanzasFamily.model.Transaccion;
import com.co.FinanzasFamily.service.TransaccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://192.168.1.1:5173"
})
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // 🔄 Obtener todas o por mes
    @GetMapping
    public List<Transaccion> listar(@RequestParam(required = false) Integer mes) {
        if (mes != null) {
            return transaccionService.listarPorMes(mes);
        } else {
            return transaccionService.listar();
        }
    }

    // ➕ Guardar nueva transacción
    @PostMapping
    public Transaccion guardar(@RequestBody Transaccion transaccion) {
        return transaccionService.guardar(transaccion);
    }
}

