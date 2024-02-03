package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
public class OficinaDTO {
    @Schema(description = "Identificador de la oficina", example = "1")
    private long id;

    @Schema(description = "Nombre de la oficina", example = "Oficina Central")
    private String nombre;

    @Schema(description = "Dirección de la oficina", example = "Calle Principal 123")
    private String direccion;

    @Schema(description = "Ciudad de la oficina", example = "Ciudad Principal")
    private String ciudad;

    @Schema(description = "Teléfono de la oficina", example = "123456789")
    private String telefono;

    @Schema(description = "Código postal de la oficina", example = "12345")
    private String codigoPostal;

    @Schema(description = "País de la oficina", example = "País Principal")
    private String pais;

    @Schema(description = "Fecha de apertura de la oficina")
    private LocalDate fechaApertura;

  
}
