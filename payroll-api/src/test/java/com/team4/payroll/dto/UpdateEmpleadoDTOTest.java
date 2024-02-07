package com.team4.payroll.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class UpdateEmpleadoDTOTest {

    @Test
     void testUpdateEmpleadoDTOFields() {
        // Create an instance of UpdateEmpleadoDTO
        UpdateEmpleadoDTO dto = new UpdateEmpleadoDTO();
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setEmail("juan@email.com");
        dto.setPuesto("Developer");
        dto.setDepartamento("IT");

        // Assert that the DTO fields are set correctly
        assertEquals("Juan", dto.getNombre());
        assertEquals("Perez", dto.getApellido());
        assertEquals("juan@email.com", dto.getEmail());
        assertEquals("Developer", dto.getPuesto());
        assertEquals("IT", dto.getDepartamento());
    }

    @Test
     void testValidation() {
        // Create an instance of UpdateEmpleadoDTO with invalid data
        UpdateEmpleadoDTO dto = new UpdateEmpleadoDTO();
        dto.setNombre(null); // Blank name should fail validation
        dto.setApellido("Perez");
        dto.setEmail("invalid-email"); // Invalid email format should fail validation
        dto.setPuesto("Developer");
        dto.setDepartamento("IT");

        // Validate the DTO using Bean Validation (JSR 380)
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(dto);

        // Assert that validation violations occur as expected
        assertEquals(2, violations.size()); // Expecting 2 violations due to blank nombre and invalid email

        
    }
}