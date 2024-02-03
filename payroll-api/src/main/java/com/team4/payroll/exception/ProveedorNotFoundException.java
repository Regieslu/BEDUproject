package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class ProveedorNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public ProveedorNotFoundException(Long id) {
        super("ERR_PROVEEDOR_NOT_FOUND", "No se puede encontrar el proveedor con id", id);
    }
}
