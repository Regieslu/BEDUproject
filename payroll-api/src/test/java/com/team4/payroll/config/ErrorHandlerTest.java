package com.team4.payroll.config;

import com.team4.payroll.dto.ErrorDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.exception.RuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private EmpleadoNotFoundException empleadoNotFoundException;

    @Mock
    private RuntimeException runtimeException;

 

    @Mock
    private Exception exception; // Mock Exception object

    @Mock
    private Logger logger; // Mock Logger object

    @InjectMocks
    private ErrorHandler errorHandler;

   


    @Test
    void unknownError() {
        // Mock behavior of the exception
        when(exception.getMessage()).thenReturn("errorMessage");

        ErrorDTO errorDTO = errorHandler.unknownError(exception);

        assertEquals("ERR_UNKNOWN", errorDTO.getCode());
        assertEquals("Ocurrió un error inesperado", errorDTO.getMessage());
        assertEquals(null, errorDTO.getDetails());
    }

    @Test
    void validationError() {
        BindingResult bindingResult = mock(BindingResult.class);
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "fieldName", "errorMessage"));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        ErrorDTO errorDTO = errorHandler.validationError(methodArgumentNotValidException);

        assertEquals("ERR_VALID", errorDTO.getCode());
        assertEquals("Hubo un error al procesar los datos de entrada", errorDTO.getMessage());
        
    }

    @Test
    void notFoundError() {
        when(empleadoNotFoundException.getCode()).thenReturn("errorCode");
        when(empleadoNotFoundException.getMessage()).thenReturn("errorMessage");
        List<String> details = new ArrayList<>();
        details.add("detail");
        when(empleadoNotFoundException.getDetails()).thenReturn(details);

        ErrorDTO errorDTO = errorHandler.notFoundError(empleadoNotFoundException);

        assertEquals("errorCode", errorDTO.getCode());
        assertEquals("errorMessage", errorDTO.getMessage());
        assertEquals(details, errorDTO.getDetails());
    }

    @Test
    void applicationError() {
        when(runtimeException.getCode()).thenReturn("errorCode");
        when(runtimeException.getMessage()).thenReturn("errorMessage");
        List<String> details = new ArrayList<>();
        details.add("detail");
        when(runtimeException.getDetails()).thenReturn(details);

        ErrorDTO errorDTO = errorHandler.applicationError(runtimeException);

        assertEquals("errorCode", errorDTO.getCode());
        assertEquals("errorMessage", errorDTO.getMessage());
        assertEquals(details, errorDTO.getDetails());
    }


}
