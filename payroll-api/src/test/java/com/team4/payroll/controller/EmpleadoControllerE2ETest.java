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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

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
        assertEquals(2, response.size());
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

    @Test
    @DisplayName("GET /empleados/{id} should return the correct employee")
    void getEmpleadoByIdTest() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setNombre("John");
        empleado.setApellido("Doe");
        empleado.setEmail("john.doe@example.com");
        empleado.setPuesto("Software Engineer");
        empleado.setDepartamento("Engineering");

        Empleado savedEmpleado = repository.save(empleado);

        MvcResult result = mockMvc.perform(get("/empleados/{id}", savedEmpleado.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        EmpleadoDTO response = mapper.readValue(content, EmpleadoDTO.class);

        assertEquals(empleado.getNombre(), response.getNombre());
        assertEquals(empleado.getApellido(), response.getApellido());
        // Add more assertions as needed
    }

    @Test
    @DisplayName("PUT /empleados/{id} should update the employee record")
    void updateEmpleadoTest() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setNombre("Jane");
        empleado.setApellido("Doe");
        empleado.setEmail("jane.doe@example.com");
        empleado.setPuesto("Product Manager");
        empleado.setDepartamento("Management");

        Empleado savedEmpleado = repository.save(empleado);

        // Update empleado data
        savedEmpleado.setNombre("Jane Updated");
        savedEmpleado.setApellido("Doe Updated");
        savedEmpleado = repository.save(empleado);

        mockMvc.perform(put("/empleados/{id}", savedEmpleado.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(savedEmpleado)))
                .andExpect(status().isOk());

        //
        Empleado updatedEmpleado = repository.findById(savedEmpleado.getId()).orElse(null);
        assertNotNull(updatedEmpleado);
        assertEquals("Jane Updated", updatedEmpleado.getNombre());
        assertEquals("Doe Updated", updatedEmpleado.getApellido());

    }

    @Test
    @DisplayName("POST /empleados should create a new employee")
    void createEmployeeTest() throws Exception {
        String requestBody = "{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\",\"puesto\":\"Desarrollador\",\"departamento\":\"IT\"}";

        mockMvc.perform(post("/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /empleados/{id} should return the correct employee")
    void getEmployeeByIdTest() throws Exception {
        // Crear un empleado para buscar por su ID
        String requestBody = "{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\",\"puesto\":\"Desarrollador\",\"departamento\":\"IT\"}";
    
        // Realizar la solicitud POST para crear un nuevo empleado
        MvcResult postResult = mockMvc.perform(post("/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
    
        // Obtener el ID del empleado creado a partir de la respuesta
        String employeeId = postResult.getResponse().getContentAsString();
    
        // Realizar una solicitud GET para obtener el empleado por su ID usando el ID almacenado
        mockMvc.perform(get("/empleados/{id}", employeeId))
                .andExpect(status().isOk());
    }
    


    @Test
    @DisplayName("PUT /empleados/{id} should update the employee record")
    void updateEmployeeTest() throws Exception {
        // Crear un empleado para luego actualizarlo
        String requestBody = "{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\",\"puesto\":\"Desarrollador\",\"departamento\":\"IT\"}";

        mockMvc.perform(post("/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        // Realizar una solicitud PUT para actualizar los detalles del empleado
        String updatedRequestBody = "{\"nombre\":\"Juan Actualizado\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\",\"puesto\":\"Desarrollador Senior\",\"departamento\":\"IT\"}";

        mockMvc.perform(put("/empleados/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedRequestBody))
                .andExpect(status().isOk());
    }

}