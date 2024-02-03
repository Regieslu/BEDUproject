package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductoDTO {
    @Schema(description = "Identificador del producto", example = "1")
    private long id;

    @Schema(description = "Nombre del producto", example = "Producto X")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Este es un producto de alta calidad")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "99.99")
    private double precio;

    @Schema(description = "Cantidad en stock del producto", example = "100")
    private int cantidadStock;

    @Schema(description = "Proveedor del producto")
    private ProveedorDTO proveedor;

  
}
