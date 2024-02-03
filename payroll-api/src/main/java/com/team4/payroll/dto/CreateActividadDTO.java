package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateActividadDTO {

    @Schema(description = "Descripción de la actividad", example = "Reunión de equipo")
    @NotBlank
    private String descripcion;

    @Schema(description = "Fecha de inicio de la actividad")
    private LocalDateTime fechaInicio;

    @Schema(description = "Fecha de fin de la actividad")
    private LocalDateTime fechaFin;

    @Schema(description = "Detalles de la actividad", example = "Reunión para discutir los proyectos pendientes")
    @NotBlank
    private String detalles;

    @Schema(description = "ID del empleado asociado a la actividad", example = "1")
    private long empleadoId;

    
}
