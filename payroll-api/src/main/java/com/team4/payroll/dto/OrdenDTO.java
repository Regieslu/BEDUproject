package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

@Data
public class OrdenDTO {
    @Schema(description = "Identificador de la orden", example = "1")
    private long id;

    @Schema(description = "Fecha de la orden")
    private Date fecha;

    @Schema(description = "Total de la orden", example = "100.0")
    private double total;

    @Schema(description = "Estado de la orden", example = "En proceso")
    private String estado;

    @Schema(description = "Dirección de entrega", example = "Calle Principal 123")
    private String direccionEntrega;

    @Schema(description = "Método de pago", example = "Tarjeta de crédito")
    private String metodoPago;

    @Schema(description = "Cliente asociado a la orden")
    private ClienteDTO cliente;

    @Schema(description = "Producto asociado a la orden")
    private ProductoDTO producto;

  
}
