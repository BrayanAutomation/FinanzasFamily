package com.co.FinanzasFamily.service;

import com.co.FinanzasFamily.model.GastoMensual;
import com.co.FinanzasFamily.repository.GastoMensualRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoMensualService {

    private final GastoMensualRepository repo;

    public GastoMensualService(GastoMensualRepository repo) {
        this.repo = repo;
    }

    public List<GastoMensual> listar(int mes, int anio) {
        return repo.findByMesAndAnio(mes, anio);
    }

    public GastoMensual guardar(GastoMensual gasto) {
        GastoMensual guardado = repo.save(gasto);

        // Crear el mismo gasto en el mes siguiente si no existe ya
        int mesSiguiente = gasto.getMes() == 12 ? 1 : gasto.getMes() + 1;
        int anioSiguiente = gasto.getMes() == 12 ? gasto.getAnio() + 1 : gasto.getAnio();

        boolean yaExiste = repo.findByMesAndAnio(mesSiguiente, anioSiguiente).stream()
                .anyMatch(g -> g.getDescripcion().equalsIgnoreCase(gasto.getDescripcion())
                        && g.getValor() == gasto.getValor());

        if (!yaExiste) {
            GastoMensual copia = new GastoMensual();
            copia.setDescripcion(gasto.getDescripcion());
            copia.setValor(gasto.getValor());
            copia.setPagado(false);
            copia.setMes(mesSiguiente);
            copia.setAnio(anioSiguiente);
            repo.save(copia);
        }

        return guardado;
    }

    public void eliminar(Long id) {
        Optional<GastoMensual> gastoOpt = repo.findById(id);

        if (gastoOpt.isPresent()) {
            GastoMensual gasto = gastoOpt.get();
            repo.deleteById(id);

            // Eliminar también del mes siguiente si coincide descripción y valor
            int mesSiguiente = gasto.getMes() == 12 ? 1 : gasto.getMes() + 1;
            int anioSiguiente = gasto.getMes() == 12 ? gasto.getAnio() + 1 : gasto.getAnio();

            repo.findByMesAndAnio(mesSiguiente, anioSiguiente).stream()
                    .filter(g -> g.getDescripcion().equalsIgnoreCase(gasto.getDescripcion())
                            && g.getValor() == gasto.getValor())
                    .findFirst()
                    .ifPresent(g -> repo.deleteById(g.getId()));
        }
    }

    public GastoMensual marcarPago(Long id, boolean pagado) {
        GastoMensual gasto = repo.findById(id).orElseThrow();
        gasto.setPagado(pagado);
        return repo.save(gasto);
    }

    public void copiarGastosDeMesAnterior(int mesActual, int anioActual) {
        List<GastoMensual> existentes = repo.findByMesAndAnio(mesActual, anioActual);
        if (!existentes.isEmpty()) return;

        int mesAnterior = mesActual == 1 ? 12 : mesActual - 1;
        int anioAnterior = mesActual == 1 ? anioActual - 1 : anioActual;

        List<GastoMensual> anteriores = repo.findByMesAndAnio(mesAnterior, anioAnterior);

        for (GastoMensual gasto : anteriores) {
            GastoMensual nuevo = new GastoMensual();
            nuevo.setDescripcion(gasto.getDescripcion());
            nuevo.setValor(gasto.getValor());
            nuevo.setPagado(false);
            nuevo.setMes(mesActual);
            nuevo.setAnio(anioActual);
            repo.save(nuevo);
        }
    }
}

