package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class OrdenNotFoundException extends com.team4.payroll.exception.RuntimeException{
    public OrdenNotFoundException(Long id) {
        super("ERR_ORDEN_NOT_FOUND", "No se puede encontrar la orden con id", id);
    }
}
