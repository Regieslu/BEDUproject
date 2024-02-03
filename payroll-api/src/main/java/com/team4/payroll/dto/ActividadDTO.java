package com.team4.payroll.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActividadDTO {
    @Schema(description = "Identificador de la actividad", example = "1")
    private long id;

    @Schema(description = "Descripción de la actividad")
    private String descripcion;

    @Schema(description = "Fecha de inicio de la actividad")
    private LocalDateTime fechaInicio;

    @Schema(description = "Fecha de fin de la actividad")
    private LocalDateTime fechaFin;

    @Schema(description = "Detalles de la actividad")
    private String detalles;

    @Schema(description = "Información del empleado asociado a la actividad")
    private EmpleadoDTO empleado; // Referencia al DTO de Empleado
}
