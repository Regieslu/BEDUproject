package com.team4.payroll.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class CreateEmpleadoDTOTest {

    @Test
     void testCreateEmpleadoDTO() {
        // Create an instance of CreateEmpleadoDTO
        CreateEmpleadoDTO dto = new CreateEmpleadoDTO();
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setEmail("juan@email.com");
        dto.setPuesto("Developer");
        dto.setDepartamento("IT");

        // Perform assertions on the DTO fields
        assertEquals("Juan", dto.getNombre());
        assertEquals("Perez", dto.getApellido());
        assertEquals("juan@email.com", dto.getEmail());
        assertEquals("Developer", dto.getPuesto());
        assertEquals("IT", dto.getDepartamento());
    }

    @Test
     void testValidation() {
        // Create an instance of CreateEmpleadoDTO with invalid data
        CreateEmpleadoDTO dto = new CreateEmpleadoDTO();
        dto.setNombre(""); // Blank name should fail validation
        dto.setApellido("Perez");
        dto.setEmail("invalid-email"); // Invalid email format should fail validation
        dto.setPuesto("Developer");
        dto.setDepartamento("IT");

        // Validate the DTO using Bean Validation (JSR 380)
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var violations = validator.validate(dto);

        // Perform assertions on the validation violations
        assertEquals(2, violations.size()); // Expecting 2 violations due to blank nombre and invalid email
    }
}
