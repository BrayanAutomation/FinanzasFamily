package com.co.FinanzasFamily.repository;

import com.co.FinanzasFamily.model.GastoMensual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoMensualRepository extends JpaRepository<GastoMensual, Long> {
    List<GastoMensual> findByMesAndAnio(int mes, int anio);
}


