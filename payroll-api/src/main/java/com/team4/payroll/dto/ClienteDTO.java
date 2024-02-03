package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteDTO {
    @Schema(description = "Identificador del cliente", example = "1")
    private long id;

    @Schema(description = "Nombre del cliente", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Perez")
    private String apellido;

    @Schema(description = "Correo electrónico del cliente", example = "juan@email.com")
    private String correoElectronico;

    @Schema(description = "Teléfono del cliente", example = "123456789")
    private String telefono;

    @Schema(description = "Dirección del cliente")
    private String direccion;

    @Schema(description = "Ciudad del cliente")
    private String ciudad;

    @Schema(description = "Código postal del cliente")
    private String codigoPostal;

  
}
