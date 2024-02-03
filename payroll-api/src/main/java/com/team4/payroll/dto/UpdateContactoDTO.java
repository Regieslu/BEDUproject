package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateContactoDTO {
      @Schema(description = "Nombre del contacto", example = "Juan")
    @NotBlank
    private String nombre;

    @Schema(description = "Apellido del contacto", example = "Perez")
    private String apellido;

    @Schema(description = "Correo electrónico del contacto", example = "juan@email.com")
    @Email
    @NotBlank
    private String correoElectronico;

    @Schema(description = "Número de teléfono del contacto", example = "123456789")
    @NotBlank
    private String telefono;

    @Schema(description = "Cargo del contacto", example = "Gerente de Ventas")
    @NotBlank
    private String cargo;

    @Schema(description = "Departamento del contacto", example = "Ventas")
    private String departamento;

    @Schema(description = "Empresa del contacto", example = "ACME Inc.")
    @NotBlank
    private String empresa;

    @Schema(description = "Dirección del contacto", example = "Calle 123")
    private String direccion;

    @Schema(description = "Ciudad del contacto", example = "Ciudad de México")
    private String ciudad;

    @Schema(description = "País del contacto", example = "México")
    private String pais;

    @Schema(description = "ID del empleado asociado al contacto", example = "1")
    private long empleadoId;
}
