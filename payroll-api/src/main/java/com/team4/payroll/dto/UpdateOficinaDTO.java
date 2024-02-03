package com.team4.payroll.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOficinaDTO {
 @Schema(description = "Nombre de la oficina", example = "Oficina Central")
    @NotBlank
    private String nombre;

    @Schema(description = "Dirección de la oficina", example = "123 Main Street")
    @NotBlank
    private String direccion;

    @Schema(description = "Ciudad de la oficina", example = "Ciudad Principal")
    @NotBlank
    private String ciudad;

    @Schema(description = "Teléfono de la oficina", example = "123-456-7890")
    @NotBlank
    private String telefono;

    @Schema(description = "Código postal de la oficina", example = "12345")
    @NotBlank
    private String codigoPostal;

    @Schema(description = "País de la oficina", example = "Pais Principal")
    @NotBlank
    private String pais;

    @Schema(description = "Fecha de apertura de la oficina", example = "2022-01-01")
    private LocalDate fechaApertura;   
}
