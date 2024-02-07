package com.team4.payroll.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class OpenAPIConfigIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
     void testSwaggerDocumentationEndpoint() {
        String url = "http://localhost:" + port + "/v3/api-docs";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Assert that the response status code is 200 OK
        assertEquals(200, response.getStatusCodeValue());

       
    }
}
