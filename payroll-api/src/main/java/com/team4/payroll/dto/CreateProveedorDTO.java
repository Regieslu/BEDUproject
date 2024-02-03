package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProveedorDTO {

    @Schema(description = "Nombre del proveedor", example = "Proveedor X")
    @NotBlank
    private String nombre;

    @Schema(description = "Dirección del proveedor", example = "Calle Principal 123")
    @NotBlank
    private String direccion;

    @Schema(description = "Teléfono del proveedor", example = "123-456-7890")
    @NotBlank
    private String telefono;

    @Schema(description = "Correo electrónico del proveedor", example = "proveedor@example.com")
    @NotBlank
    @Email
    private String correoElectronico;

    @Schema(description = "Tipo de servicio del proveedor", example = "Suministro de materiales")
    @NotBlank
    private String tipoServicio;

    
}
