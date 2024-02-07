package com.team4.payroll.service;

import com.team4.payroll.dto.CreateEmpleadoDTO;
import com.team4.payroll.dto.EmpleadoDTO;
import com.team4.payroll.dto.UpdateEmpleadoDTO;
import com.team4.payroll.exception.EmpleadoNotFoundException;
import com.team4.payroll.mapper.EmpleadoMapper;
import com.team4.payroll.model.Empleado;
import com.team4.payroll.repository.EmpleadoRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
class EmpleadoServiceTest {

    @MockBean
    private EmpleadoRepository repository;

    @Autowired
    private EmpleadoService service;

    @Autowired
    @Mock
    private EmpleadoMapper mapper; 
    


    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return employee from repository")
    void findAllTest() {
        List<Empleado> data = new LinkedList<>();

        Empleado empleado = new Empleado();

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

//    @Test
//    @DisplayName("Service should delete an employee by id in repository")
//    void deleteByIdTest() throws EmpleadoNotFoundException {
//        service.deleteById(3L);
//
//        verify(repository, times(1)).deleteById(3l);
//    }

    @Test
    @DisplayName("Test findById when employee exists")
    void testFindByIdWhenExists() throws EmpleadoNotFoundException {
        // Mock repository to return an Empleado object with sample values
        EmpleadoDTO empleadoDto = new EmpleadoDTO();
        empleadoDto.setId(33L);
        empleadoDto.setNombre("John");
        empleadoDto.setApellido("Doe");
        empleadoDto.setDepartamento("IT");
        empleadoDto.setEmail("Doe@it.com");
        empleadoDto.setPuesto("Leader");

        Empleado model = new Empleado();
        model.setId(33L); // Set the correct ID
        model.setNombre(empleadoDto.getNombre());
        model.setApellido(empleadoDto.getApellido());
        model.setDepartamento(empleadoDto.getDepartamento());
        model.setEmail(empleadoDto.getEmail());
        model.setPuesto(empleadoDto.getPuesto());

        // Mock repository
        when(repository.findById(anyLong())).thenReturn(Optional.of(model));

        // Call the method being tested
        EmpleadoDTO result = service.findById(33L); // Pass the correct ID

        // Assert the result
        assertEquals(empleadoDto, result);
    }
    
    

    @Test  
    @DisplayName("Test should trown an excetion if not exist")
    void testFindByIdWhenNotExists() {
        // Mock repository
        EmpleadoRepository repository = mock(EmpleadoRepository.class);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Create EmpleadoService with the mocked repository
        EmpleadoService service = new EmpleadoService(repository, mapper);

        // Call the method being tested and assert that it throws EmpleadoNotFoundException
        assertThrows(EmpleadoNotFoundException.class, () -> {
            service.findById(1L);
        });
    }


//    @Test
//void testDeleteByIdWhenEmployeeExists() throws EmpleadoNotFoundException {
//    Long testID = anyLong();
//    // Mock the repository to return true when existsById is called
//    when(repository.existsById(testID)).thenReturn(true);
//
//    // Execute the method and expect EmpleadoNotFoundException to be thrown
//    assertThrows(EmpleadoNotFoundException.class, () -> {
//        service.deleteById(testID-1L); // Pass any ID you want to test
//    });
//
//    // Verify that deleteById method is not called
//    verify(repository, never()).deleteById(testID);
//}

@Test
void testUpdateWhenEmployeeNotFound() {
    // Mock the repository to return an empty Optional
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    // Execute the method and expect EmpleadoNotFoundException to be thrown
    assertThrows(EmpleadoNotFoundException.class, () -> {
        service.update(1L, new UpdateEmpleadoDTO()); // Pass any ID you want to test
    });

    // Verify that update method of mapper and save method of repository are not called
    verify(mapper, never()).update(any(Empleado.class), any(UpdateEmpleadoDTO.class));
    verify(repository, never()).save(any(Empleado.class));
}



}
