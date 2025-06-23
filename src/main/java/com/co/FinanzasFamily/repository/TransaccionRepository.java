package com.co.FinanzasFamily.repository;

import com.co.FinanzasFamily.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByMes(int mes);
}
