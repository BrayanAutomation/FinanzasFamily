package com.co.FinanzasFamily.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumen_mensual")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenMensual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mes;
    private Integer anio;
    private Boolean completo;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
}

