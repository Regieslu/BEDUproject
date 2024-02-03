package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class UsuarioNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("ERR_USUARIO_NOT_FOUND", "No se puede encontrar el usuario con id", id);
    } 
}
