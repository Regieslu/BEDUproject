package com.team4.payroll.service;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.model.Empleado;
import com.team4.payroll.repository.EmpleadoRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmpleadoServiceTest {

    @MockBean
    private EmpleadoRepository repository;

    @Autowired
    private EmpleadoService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest (){
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return employee from repository")
            void findAllTest() {
        List<Empleado> data = new LinkedList<>();

        Empleado empleado =  new Empleado();

        empleado.setId(9);
        empleado.setNombre("Ramiro");
        empleado.setApellido("Pulido");

        data.add(empleado);

        when(repository.findAll()).thenReturn(data);

        List<EmpleadoDTO> result = service.findAll();
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(empleado.getId(), result.get(0).getId());
        assertEquals(empleado.getNombre(), result.get(0).getNombre());
        assertEquals(empleado.getApellido(), result.get(0).getApellido());
        assertEquals(empleado.getDepartamento(), result.get(0).getDepartamento());
        assertEquals(empleado.getPuesto(), result.get(0).getPuesto());
        assertEquals(empleado.getEmail(), result.get(0).getEmail());
    }

    @Test
    @DisplayName("Service should save an employee in repository")
    void saveTest() {
        CreateEmpleadoDTO dto = new CreateEmpleadoDTO();

        dto.setNombre("Ernesto");
        dto.setApellido("Milla");

        Empleado model = new Empleado();

        model.setId(4);
        model.setNombre(dto.getNombre());
        model.setApellido((dto.getApellido()));

        when(repository.save(any(Empleado.class))).thenReturn(model);

        EmpleadoDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getNombre(), result.getNombre());
        assertEquals(model.getApellido(), result.getApellido());
        assertEquals(model.getDepartamento(), result.getDepartamento());
        assertEquals(model.getPuesto(), result.getPuesto());
        assertEquals(model.getEmail(), result.getEmail());
    }
    @Test
    @DisplayName("Service should update an employee in repository")
    void updateTest() throws EmpleadoNotFoundException {
        UpdateEmpleadoDTO dto = new UpdateEmpleadoDTO();

        dto.setNombre("Alberto");
        dto.setApellido("Villa");

        Empleado empleado = new Empleado();

        empleado.setId(6);
        empleado.setNombre("Alan");
        empleado.setApellido("Lara");

        when(repository.findById(anyLong())).thenReturn(Optional.of(empleado));

        service.update(6L, dto);

        assertEquals(dto.getNombre(), empleado.getNombre());
        assertEquals(dto.getApellido(), empleado.getApellido());
        verify(repository, times(1)).save(empleado);
    }

    @Test
    @DisplayName("Service should delete an employee by id in repository")
    void deleteByIdTest() throws EmpleadoNotFoundException {
        service.deleteById(3L);

        verify(repository, times(1)).deleteById(3l);
    }
}

