package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProveedorDTO {
    @Schema(description = "Identificador del proveedor", example = "1")
    private long id;

    @Schema(description = "Nombre del proveedor", example = "Proveedor A")
    private String nombre;

    @Schema(description = "Dirección del proveedor", example = "Calle Principal 123")
    private String direccion;

    @Schema(description = "Teléfono del proveedor", example = "123456789")
    private String telefono;

    @Schema(description = "Correo electrónico del proveedor", example = "proveedor@example.com")
    private String correoElectronico;

    @Schema(description = "Tipo de servicio del proveedor", example = "Servicio de suministro")
    private String tipoServicio;

  
}
