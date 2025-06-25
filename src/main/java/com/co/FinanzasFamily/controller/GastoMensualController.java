package com.co.FinanzasFamily.controller;

import com.co.FinanzasFamily.model.GastoMensual;
import com.co.FinanzasFamily.service.GastoMensualService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos-mensuales")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "finanzas-family-front.vercel.app"
})
public class GastoMensualController {

    private final GastoMensualService service;

    public GastoMensualController(GastoMensualService service) {
        this.service = service;
    }

    @GetMapping
    public List<GastoMensual> listar(
            @RequestParam int mes,
            @RequestParam int anio
    ) {
        return service.listar(mes, anio);
    }

    @PostMapping
    public GastoMensual guardar(@RequestBody GastoMensual gasto) {
        return service.guardar(gasto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/{id}/pagar")
    public GastoMensual pagar(@PathVariable Long id, @RequestParam boolean pagado) {
        return service.marcarPago(id, pagado);
    }

    @PostMapping("/copiar")
    public void copiarGastosDelMesAnterior(@RequestParam int mes, @RequestParam int anio) {
        service.copiarGastosDeMesAnterior(mes, anio);
    }

}
