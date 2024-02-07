package com.team4.payroll.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RuntimeExceptionTest {

      @Test
    void constructorAndGetters() {
        // Test data
        String code = "ERR_CODE";
        String message = "Error message";
        Object details = new Object();

        // Create RuntimeException instance
        RuntimeException exception = new RuntimeException(code, message, details);

        // Verify that fields are set correctly
        assertEquals(code, exception.getCode());
        assertEquals(message, exception.getMessage());
        assertEquals(details, exception.getDetails());
    }

    @Test
    void constructorNullDetails() {
        // Test data
        String code = "ERR_CODE";
        String message = "Error message";

        // Create RuntimeException instance with null details
        RuntimeException exception = new RuntimeException(code, message, null);

        // Verify that fields are set correctly
        assertEquals(code, exception.getCode());
        assertEquals(message, exception.getMessage());
        // Assert that details field is null
        assertNull(exception.getDetails());
    }
}
