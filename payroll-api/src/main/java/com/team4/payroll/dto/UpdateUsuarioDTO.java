package com.team4.payroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUsuarioDTO {
        @NotBlank
    @Schema(description = "Nombre del usuario", example = "John")
    private String nombre;

    @NotBlank
    @Schema(description = "Apellido del usuario", example = "Doe")
    private String apellido;

    @NotBlank
    @Email
    @Schema(description = "Correo electrónico del usuario", example = "john.doe@example.com")
    private String correoElectronico;

    @Schema(description = "Nombre de usuario", example = "johndoe123")
    private String nombreUsuario;

    @NotBlank
    @Schema(description = "Contraseña del usuario")
    private String contrasena;

    @Schema(description = "Indica si el usuario es administrador", defaultValue = "false")
    private Boolean isAdmin;
}
