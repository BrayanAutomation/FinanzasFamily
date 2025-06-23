package com.co.FinanzasFamily.service;

import com.co.FinanzasFamily.model.ResumenMensual;
import com.co.FinanzasFamily.repository.ResumenMensualRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResumenMensualService {

    private final ResumenMensualRepository repo;

    public ResumenMensualService(ResumenMensualRepository repo) {
        this.repo = repo;
    }

    public Optional<ResumenMensual> obtenerResumen(int mes, int anio) {
        return repo.findByMesAndAnio(mes, anio);
    }

    public ResumenMensual cerrarMes(int mes, int anio, boolean completo) {
        Optional<ResumenMensual> existente = repo.findByMesAndAnio(mes, anio);
        if (existente.isPresent()) {
            return existente.get(); // ya fue cerrado
        }

        ResumenMensual resumen = new ResumenMensual();
        resumen.setMes(mes);
        resumen.setAnio(anio);
        resumen.setCompleto(completo);
        resumen.setFechaCierre(LocalDateTime.now());
        return repo.save(resumen);
    }

    public void eliminarCierre(int mes, int anio) {
        repo.findByMesAndAnio(mes, anio)
                .ifPresent(resumen -> repo.deleteById(resumen.getId()));
    }

    public List<ResumenMensual> listarTodos() {
        return repo.findAllByOrderByAnioDescMesDesc();
    }

    public void actualizarCompleto(Long id, boolean completo) {
        ResumenMensual resumen = repo.findById(id).orElseThrow();
        resumen.setCompleto(completo);
        repo.save(resumen);
    }
}
