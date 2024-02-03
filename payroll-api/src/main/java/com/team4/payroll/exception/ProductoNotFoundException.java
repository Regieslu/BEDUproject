package com.team4.payroll.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "EmpleadoNotFoundException reason")
public class ProductoNotFoundException  extends com.team4.payroll.exception.RuntimeException {
    public ProductoNotFoundException(Long id) {
        super("ERR_PRODUCTO_NOT_FOUND", "No se puede encontrar el producto con id", id);
    }
}
