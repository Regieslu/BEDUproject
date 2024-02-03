package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class ContactoNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public ContactoNotFoundException(Long id) {
        super("ERR_CONTACTO_NOT_FOUND", "No se puede encontrar el contacto con id", id);
    }
    
}
