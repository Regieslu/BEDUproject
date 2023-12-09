package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmpleadoDTO {
    @Schema(description = "Identificador del empleado", example = "1")
    private long id;
    @Schema(description = "Nombre del empleado", example = "Juan")
    private String nombre;
    @Schema(description = "Apellido del empleado", example = "Perez")
    private String apellido;
    @Schema(description = "Email del empleado", example = "juan@email.com")
    private String email;
}
