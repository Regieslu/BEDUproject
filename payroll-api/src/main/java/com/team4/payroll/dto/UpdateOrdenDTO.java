package com.team4.payroll.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOrdenDTO {
     @Schema(description = "Identificador de la orden", example = "1")
    @NotBlank
    private long id;

    @Schema(description = "Fecha de la orden", example = "2024-02-01")
    @NotBlank
    private Date fecha;

    @Schema(description = "Total de la orden", example = "100.00")
    @NotBlank
    private double total;

    @Schema(description = "Estado de la orden", example = "Pendiente")
    @NotBlank
    private String estado;

    @Schema(description = "Dirección de entrega de la orden", example = "123 Main Street")
    @NotBlank
    private String direccionEntrega;

    @Schema(description = "Método de pago de la orden", example = "Tarjeta de crédito")
    @NotBlank
    private String metodoPago;
}
