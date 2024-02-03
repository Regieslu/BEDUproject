package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class ActividadNotFoundException  extends com.team4.payroll.exception.RuntimeException {
    public ActividadNotFoundException(Long id) {
        super("ERR_ACTIVIDAD_NOT_FOUND", "No se puede encontrar la actividad con id", id);
    }
}
