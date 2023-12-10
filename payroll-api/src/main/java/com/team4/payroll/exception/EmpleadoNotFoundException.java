package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class EmpleadoNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public EmpleadoNotFoundException(Long id) {
        super("ERR_EMPLEADO_NOT_FOUND", "No se puede encontrar el empleado con id", id);
    }
}
