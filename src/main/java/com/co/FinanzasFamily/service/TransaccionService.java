package com.co.FinanzasFamily.service;

import com.co.FinanzasFamily.model.Transaccion;
import com.co.FinanzasFamily.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<Transaccion> listar() {
        return transaccionRepository.findAll();
    }

    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> listarPorMes(int mes) {
        return transaccionRepository.findByMes(mes);
    }

}
