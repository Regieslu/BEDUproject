package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioDTO {
    @Schema(description = "Identificador del usuario", example = "1")
    private long id;

    @Schema(description = "Nombre del usuario", example = "John")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Doe")
    private String apellido;

    @Schema(description = "Correo electrónico del usuario", example = "john.doe@example.com")
    private String correoElectronico;

    @Schema(description = "Nombre de usuario", example = "john_doe")
    private String nombreUsuario;

    @Schema(description = "Contraseña del usuario", example = "********")
    private String contrasena;

    @Schema(description = "Indicador de administrador", example = "false")
    private Boolean isAdmin;

  
}
