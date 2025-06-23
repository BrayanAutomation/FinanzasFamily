package com.co.FinanzasFamily.repository;

import com.co.FinanzasFamily.model.ResumenMensual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumenMensualRepository extends JpaRepository<ResumenMensual, Long> {
    Optional<ResumenMensual> findByMesAndAnio(int mes, int anio);

    List<ResumenMensual> findAllByOrderByAnioDescMesDesc();
}

