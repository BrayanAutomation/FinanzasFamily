package com.co.FinanzasFamily.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gastos_mensuales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoMensual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Integer valor;
    private Boolean pagado = false;

    private Integer mes; // 1 a 12
    private Integer anio; // 2025, etc.
}
