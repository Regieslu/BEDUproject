package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProductoDTO {
    @Schema(description = "Nombre del producto", example = "Camisa")
    @NotBlank
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Camisa de algodón")
    @NotBlank
    private String descripcion;

    @Schema(description = "Precio del producto", example = "25.99")
    @NotBlank
    private double precio;

    @Schema(description = "Cantidad en stock del producto", example = "100")
    @NotBlank
    private int cantidadStock;
}
