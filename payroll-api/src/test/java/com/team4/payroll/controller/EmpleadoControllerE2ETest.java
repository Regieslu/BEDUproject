package com.team4.payroll.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.payroll.repository.EmpleadoRepository;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.ErrorDTO;
import com.team4.payroll.model.Empleado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmpleadoControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmpleadoRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /empleados should return an empty list")
    void emptyListTest() throws Exception {

        // Realizar una petición de tipo GET
        // hacia /empleados y esperar que el resultado sea 200
        MvcResult result = mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /empleados should return a list of empleados")
    void findAllTest() throws Exception {
        Empleado empleado = new Empleado();
        Empleado empleado2 = new Empleado();

        empleado.setNombre("Juan");
        empleado.setApellido("Gabriel");
        empleado.setEmail("juanga@yahoo.com");
        empleado.setPuesto("Compositor");
        empleado.setDepartamento("Letras");

        empleado2.setNombre("Joan");
        empleado2.setApellido("Sebastian");
        empleado2.setEmail("Sebastian@yahoo.com");
        empleado2.setPuesto("Compositor");
        empleado2.setDepartamento("Musica");

        repository.save(empleado);
        repository.save(empleado2);

        MvcResult result = mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<EmpleadoDTO>> listTypeReference = new TypeReference<List<EmpleadoDTO>>() {
        };

        // Convertimos el JSON a un objeto de Java
        List<EmpleadoDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertTrue(response.size() == 2);
        assertEquals(empleado.getNombre(), response.get(0).getNombre());
        assertEquals(empleado2.getDepartamento(), response.get(1).getDepartamento());
    }

    @Test
    @DisplayName("POST /empleados should return an error if name is missing")
    void titleMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc
                .perform(post("/empleados").contentType("application/json").content("{\"apellido\":\"Luna\"}"))
                
                .andReturn();
    
        String content = result.getResponse().getContentAsString();
    
        // Definir el mensaje de error esperado
        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada\",\"details\":[\"must not be blank\",\"must not be blank\",\"must not be blank\"]}";
    
        // Comparar la respuesta con el mensaje de error esperado
        assertEquals(expectedResponse, content);
    }
    

    @Test
    @DisplayName("POST /empleados should return an error if apellido is missing")
    void yearMissingInRequestBodyTest() throws Exception {
        MvcResult result = mockMvc
                .perform(post("/empleados").contentType("application/json").content("{\"nombre\":\"Emilio\"}"))
            
                .andReturn();

        // Leemos el contenido con caracteres de acentos y ñ
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        // Convertimos el JSON a un objeto de Java
        ErrorDTO response = mapper.readValue(content, ErrorDTO.class);

        assertEquals("ERR_VALID", response.getCode());
        assertEquals("Hubo un error al procesar los datos de entrada", response.getMessage());

        List<String> details = (List<String>) response.getDetails();

        assertEquals("must not be blank", details.get(0));
    }
}