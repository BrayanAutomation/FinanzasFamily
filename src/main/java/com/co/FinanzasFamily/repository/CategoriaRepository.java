package com.co.FinanzasFamily.repository;

import com.co.FinanzasFamily.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

