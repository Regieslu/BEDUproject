package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContactoDTO {
    @Schema(description = "Identificador del contacto", example = "1")
    private long id;

    @Schema(description = "Nombre del contacto", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del contacto", example = "Perez")
    private String apellido;

    @Schema(description = "Correo electrónico del contacto", example = "juan@email.com")
    private String correoElectronico;

    @Schema(description = "Teléfono del contacto", example = "123456789")
    private String telefono;

    @Schema(description = "Cargo del contacto", example = "Gerente")
    private String cargo;

    @Schema(description = "Departamento del contacto", example = "Ventas")
    private String departamento;

    @Schema(description = "Empresa del contacto", example = "Empresa ABC")
    private String empresa;

    @Schema(description = "Dirección del contacto")
    private String direccion;

    @Schema(description = "Ciudad del contacto")
    private String ciudad;

    @Schema(description = "País del contacto")
    private String pais;

  
}
