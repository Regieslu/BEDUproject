package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")

public class ClienteNotFoundException extends com.team4.payroll.exception.RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("ERR_CLIENTE_NOT_FOUND", "No se puede encontrar el cliente con id", id);
    }
}
