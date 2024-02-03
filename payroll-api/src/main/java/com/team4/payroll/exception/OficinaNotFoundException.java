package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class OficinaNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public OficinaNotFoundException(Long id) {
        super("ERR_OFICINA_NOT_FOUND", "No se puede encontrar la oficina con id", id);
    }
}
