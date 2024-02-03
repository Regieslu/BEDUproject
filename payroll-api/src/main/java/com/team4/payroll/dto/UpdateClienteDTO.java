package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateClienteDTO {
       @Schema(description = "Nombre del cliente", example = "Juan")
    @NotBlank
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Perez")
    private String apellido;

    @Schema(description = "Correo electrónico del cliente", example = "juan@email.com")
    @Email
    @NotBlank
    private String correoElectronico;

    @Schema(description = "Número de teléfono del cliente", example = "123456789")
    @NotBlank
    private String telefono;

    @Schema(description = "Dirección del cliente", example = "Calle 123")
    private String direccion;

    @Schema(description = "Ciudad del cliente", example = "Ciudad de México")
    private String ciudad;

    @Schema(description = "Código postal del cliente", example = "12345")
    private String codigoPostal;

    @Schema(description = "ID del empleado asociado al cliente", example = "1")
    private long empleadoId;
}
